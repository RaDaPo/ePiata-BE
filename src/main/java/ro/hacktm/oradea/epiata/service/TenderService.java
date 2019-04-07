package ro.hacktm.oradea.epiata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.hacktm.oradea.epiata.exception.NeededGrossMassExceeded;
import ro.hacktm.oradea.epiata.exception.TenderNotFoundException;
import ro.hacktm.oradea.epiata.model.dto.*;
import ro.hacktm.oradea.epiata.model.entity.*;
import ro.hacktm.oradea.epiata.repository.CategoryRepository;
import ro.hacktm.oradea.epiata.repository.TenderAttendeesRepository;
import ro.hacktm.oradea.epiata.repository.TenderRepository;
import ro.hacktm.oradea.epiata.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TenderService {

	private final TenderRepository repository;
	private final UserService userService;
	private final UserRepository userRepository;
	private final TenderAttendeesRepository tenderAttendeesRepository;
	private final CategoryRepository categoryRepository;

	public List<TenderResponseDto> getAllTenders() {
		return repository.findAllByActiveTrue()
				.stream()
				.filter(Objects::nonNull)
				.map(Tender::toDto)
				.collect(Collectors.toList());
	}

	public TenderResponseDto addNewTender(TenderAddRequest tenderRequestDto) {
		Tender tender = new Tender();
		BeanUtils.copyProperties(tenderRequestDto, tender);
		Optional<User> owner = userRepository.findById(tenderRequestDto.getOwnerId());
		Optional<Category> category = categoryRepository.findById(tenderRequestDto.getCategoryId());
		if (owner.isPresent()) {
			owner.ifPresent(userDao -> tender.setOwner(userDao.toOwnerDao()));
			tender.setLocation(owner.get().getLocation());
		}
		category.ifPresent(tender::setCategory);
		save(tender);
		return tender.toDto();
	}

	public Tender addAttendeesToTender(TenderAddUsersRequestDto tenderRequestDto) {
		Optional<Tender> tender = getTenderById(tenderRequestDto.getTenderId());
		if (tender.isPresent()) {
			return processIfMassStillNeeded(tenderRequestDto, tender.get());
		}
		throw new TenderNotFoundException(tenderRequestDto.getTenderId());
	}

	private Tender processIfMassStillNeeded(TenderAddUsersRequestDto tenderRequestDto, Tender tender) {
		if (getNeededGrossMassPlusMarje(tender) > tenderRequestDto.getParticipationMass()) {
			Optional<User> user = userRepository.findById(tenderRequestDto.getUserId());
			user.ifPresent(userDao -> setAttendees(tenderRequestDto, tender, userDao));
			return tender;
		}
		throw new NeededGrossMassExceeded(getNeededGrossMassPlusMarje(tender) - tender.getGatheredGrossMass());
	}

	private void setAttendees(TenderAddUsersRequestDto tenderRequestDto, Tender tender, User user) {
		tender.setGatheredGrossMass(tender.getGatheredGrossMass() + tenderRequestDto.getParticipationMass());
		TenderAttendee tenderAttendee = new TenderAttendee();
		tenderAttendee.setUserId(user.getId());
		tenderAttendee.setParticipationMass(tenderRequestDto.getParticipationMass());
		tenderAttendee.setName(user.getName());
		tender.getAllTenderAttendees().add(tenderAttendee);
		save(tender);
	}

	private int getNeededGrossMassPlusMarje(Tender tender) {
		return (tender.getNeededGrossMass() - tender.getGatheredGrossMass()) + tender.getNeededGrossMass() / 5;
	}

	public void acceptAttendeeToTender(TenderAcceptUser acceptUser) {
		Optional<Tender> tender = getTenderById(acceptUser.getTenderId());
		Optional<User> user = userRepository.findById(acceptUser.getUserId());
		if (tender.isPresent() && user.isPresent()) {
			if (tender.get().getAcceptedUserIds() != null) {
				TenderAttendee tenderAttendee = tenderAttendeesRepository.findByUserId(acceptUser.getUserId());
				if (!tenderAttendee.getRejected().equals(true)) {
					AcceptedUser acceptedUser = new AcceptedUser();
					acceptedUser.setUserId(acceptUser.getUserId());
					tender.get().getAcceptedUserIds().add(acceptedUser);
					processAndSaveAttendee(tender.get(), user.get(), tenderAttendee, acceptedUser);
				}
			} else {
				TenderAttendee tenderAttendee = tenderAttendeesRepository.findByUserId(acceptUser.getUserId());
				if (!tenderAttendee.getRejected().equals(true)) {
					AcceptedUser acceptedUser = new AcceptedUser();
					acceptedUser.setUserId(acceptUser.getUserId());
					tender.get().setAcceptedUserIds(Collections.singletonList(acceptedUser));
					processAndSaveAttendee(tender.get(), user.get(), tenderAttendee, acceptedUser);
				}
			}
		}
	}

	private void processAndSaveAttendee(Tender tender, User user, TenderAttendee tenderAttendee, AcceptedUser acceptedUser) {
		tenderAttendee.setAccepted(true);
		tenderAttendeesRepository.save(tenderAttendee);
		tender.setGatheredGrossMass(tender.getGatheredGrossMass() + tenderAttendee.getParticipationMass());
		acceptedUser.setName(user.getName());
		tender.getAcceptedUsers().add(user);
		if (tender.getGatheredGrossMass() >= tender.getNeededGrossMass() ||
				tender.getGatheredGrossMass() < (tender.getNeededGrossMass() + tender.getNeededGrossMass() / 5)) {
			tender.setActive(false);
		}
		save(tender);
	}

	public void declineAttendeeFromTender(TenderAcceptUser acceptUser) {
		Optional<Tender> tender = getTenderById(acceptUser.getTenderId());
		if (tender.isPresent() && tender.get().getAcceptedUserIds().stream().noneMatch(x -> x.getUserId().equals(acceptUser.getUserId()))) {
			TenderAttendee tenderAttendee = tenderAttendeesRepository.findByUserId(acceptUser.getUserId());
			tenderAttendee.setAccepted(false);
			tenderAttendee.setRejected(true);
			Optional<User> userDao = userRepository.findById(acceptUser.getUserId());
			userDao.ifPresent(dao -> tender.get().getAcceptedUsers().remove(dao));
			save(tender.get());
		}
	}

	private void save(Tender tender) {
		repository.save(tender);
	}

	private Optional<Tender> getTenderById(Long id) {
		return repository.findById(id);
	}

	public List<Tender> findByDescriptionContaining(String searchPhrase) {
		return repository.findByDescriptionOrTitleContainingAndActiveTrue(searchPhrase, searchPhrase);
	}

	public List<Tender> findByCategoryTypeAndCounty(TenderFilteredRequestDto requestDto) {
		return repository.findByCategory_TypeOrLocation_CountyAndActiveTrue(requestDto.getCategoryType(), requestDto.getCounty());
	}

	public void findTenderByIdAndCloseIt(TenderCloseRequestDto requestDto) {
		Optional<Tender> tender = repository.findById(requestDto.getTenderId());
		if (tender.isPresent()) {
			tender.get().setActive(false);
			repository.save(tender.get());
		}
	}

	public void deleteTender(TenderCloseRequestDto requestDto) {
		Optional<Tender> tender = repository.findById(requestDto.getTenderId());
		tender.ifPresent(repository::delete);
	}

	public void findTenderAndReactivateId(TenderCloseRequestDto requestDto) {
		Optional<Tender> tender = repository.findById(requestDto.getTenderId());
		if (tender.isPresent()) {
			tender.get().setActive(true);
			tender.get().setNeededGrossMass(requestDto.getNewNeededGrossMass());
			repository.save(tender.get());
		}
	}
}
