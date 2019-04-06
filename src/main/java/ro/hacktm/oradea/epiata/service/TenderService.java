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
	private final TenderAttendeesRepository tenderAttendeesRepository;
	private final CategoryRepository categoryRepository;

	public List<TenderResponseDto> getAllTenders() {
		return repository.findAllByActiveTrue()
				.stream()
				.filter(Objects::nonNull)
				.map(TenderDao::toDto)
				.collect(Collectors.toList());
	}

	public TenderResponseDto addNewTender(TenderAddRequest tenderRequestDto) {
		TenderDao tender = new TenderDao();
		BeanUtils.copyProperties(tenderRequestDto, tender);
		Optional<UserDao> owner = userService.getUserDaoById(tenderRequestDto.getOwnerId());
		Optional<Category> category = categoryRepository.findByName(tenderRequestDto.getCategoryName());
		if (owner.isPresent()) {
			owner.ifPresent(userDao -> tender.setOwner(userDao.toOwnerDao()));
			tender.setLocation(owner.get().getLocation());
		}
		category.ifPresent(tender::setCategory);
		save(tender);
		return tender.toDto();
	}

	public TenderDao addAttendeesToTender(TenderAddUsersRequestDto tenderRequestDto) {
		Optional<TenderDao> tender = getTenderById(tenderRequestDto.getTenderId());
		if (tender.isPresent()) {
			return processIfMassStillNeeded(tenderRequestDto, tender.get());
		}
		throw new TenderNotFoundException(tenderRequestDto.getTenderId());
	}

	private TenderDao processIfMassStillNeeded(TenderAddUsersRequestDto tenderRequestDto, TenderDao tender) {
		if (getNeededGrossMassPlusMarje(tender) > tenderRequestDto.getParticipationMass()) {
			Optional<UserDao> user = userService.getUserDaoById(tenderRequestDto.getUserId());
			user.ifPresent(userDao -> setAttendees(tenderRequestDto, tender, userDao));
			return tender;
		}
		throw new NeededGrossMassExceeded(getNeededGrossMassPlusMarje(tender) - tender.getGatheredGrossMass());
	}

	private void setAttendees(TenderAddUsersRequestDto tenderRequestDto, TenderDao tender, UserDao user) {
		tender.setGatheredGrossMass(tender.getGatheredGrossMass() + tenderRequestDto.getParticipationMass());
		TenderAttendee tenderAttendee = new TenderAttendee();
		tenderAttendee.setUserId(user.getId());
		tenderAttendee.setParticipationMass(tenderRequestDto.getParticipationMass());
		tenderAttendee.setName(user.getName());
		tender.getAllTenderAttendees().add(tenderAttendee);
		save(tender);
	}

	private int getNeededGrossMassPlusMarje(TenderDao tender) {
		return (tender.getNeededGrossMass() - tender.getGatheredGrossMass()) + tender.getNeededGrossMass() / 5;
	}

	public void acceptAttendeeToTender(TenderAcceptUser acceptUser) {
		Optional<TenderDao> tender = getTenderById(acceptUser.getTenderId());
		Optional<UserDao> user = userService.getUserDaoById(acceptUser.getUserId());
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

	private void processAndSaveAttendee(TenderDao tender, UserDao user, TenderAttendee tenderAttendee, AcceptedUser acceptedUser) {
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
		Optional<TenderDao> tender = getTenderById(acceptUser.getTenderId());
		if (tender.isPresent() && tender.get().getAcceptedUserIds().stream().noneMatch(x -> x.getUserId().equals(acceptUser.getUserId()))) {
			TenderAttendee tenderAttendee = tenderAttendeesRepository.findByUserId(acceptUser.getUserId());
			tenderAttendee.setAccepted(false);
			tenderAttendee.setRejected(true);
			Optional<UserDao> userDao = userService.getUserDaoById(acceptUser.getUserId());
			userDao.ifPresent(dao -> tender.get().getAcceptedUsers().remove(dao));
			save(tender.get());
		}
	}

	private void save(TenderDao tender) {
		repository.save(tender);
	}

	private Optional<TenderDao> getTenderById(Long id) {
		return repository.findById(id);
	}

	public List<TenderDao> findByDescriptionContaining(String searchPhrase) {
		return repository.findByDescriptionOrTitleContainingAndActiveTrue(searchPhrase, searchPhrase);
	}

	public List<TenderDao> findByCategoryTypeAndCounty(TenderFilteredRequestDto requestDto) {
		return repository.findByCategory_TypeOrLocation_CountyAndActiveTrue(requestDto.getCategoryType(), requestDto.getCounty());
	}

	public void findTenderByIdAndCloseIt(TenderCloseRequestDto requestDto) {
		Optional<TenderDao> tender = repository.findById(requestDto.getTenderId());
		if (tender.isPresent()) {
			tender.get().setActive(false);
			repository.save(tender.get());
		}
	}

	public void deleteTender(TenderCloseRequestDto requestDto) {
		Optional<TenderDao> tender = repository.findById(requestDto.getTenderId());
		tender.ifPresent(repository::delete);
	}

	public void findTenderAndReactivateId(TenderCloseRequestDto requestDto) {
		Optional<TenderDao> tender = repository.findById(requestDto.getTenderId());
		if (tender.isPresent()) {
			tender.get().setActive(true);
			repository.save(tender.get());
		}
	}
}
