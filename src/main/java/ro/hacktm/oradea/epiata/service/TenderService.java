package ro.hacktm.oradea.epiata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.hacktm.oradea.epiata.exception.NeededGrossMassExceeded;
import ro.hacktm.oradea.epiata.exception.TenderNotFoundException;
import ro.hacktm.oradea.epiata.model.dto.*;
import ro.hacktm.oradea.epiata.model.entity.AcceptedUser;
import ro.hacktm.oradea.epiata.model.entity.TenderAttendee;
import ro.hacktm.oradea.epiata.model.entity.TenderDao;
import ro.hacktm.oradea.epiata.model.entity.UserDao;
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
		if(owner.isPresent()) {
			owner.ifPresent(userDao -> tender.setOwner(userDao.toOwnerDao()));
			tender.setLocation(owner.get().getLocation());
		}
		tender.setTitle(tenderRequestDto.getTitle());
		save(tender);
		return tender.toDto();
	}

	public TenderDao addAttendeesToTender(TenderAddUsersRequestDto tenderRequestDto) {
		Optional<TenderDao> tender = getTenderById(tenderRequestDto.getTenderId());
		if(tender.isPresent()) {
			if (getNeededGrossMassPlusMarje(tender.get()) > tenderRequestDto.getParticipationMass()) {
				Optional<UserDao> user = userService.getUserDaoById(tenderRequestDto.getUserId());
				if (user.isPresent()) {
					tender.get().setGatheredGrossMass(tender.get().getGatheredGrossMass() + tenderRequestDto.getParticipationMass());
					TenderAttendee tenderAttendee = new TenderAttendee();
					tenderAttendee.setUserId(user.get().getId());
					tenderAttendee.setParticipationMass(tenderRequestDto.getParticipationMass());
					tenderAttendee.setName(user.get().getName());
					tender.get().getAllTenderAttendees().add(tenderAttendee);
					save(tender.get());
				}
				return tender.get();
			}
			throw new NeededGrossMassExceeded(getNeededGrossMassPlusMarje(tender.get()) - tender.get().getGatheredGrossMass());
		}
		throw new TenderNotFoundException(tenderRequestDto.getTenderId());
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
		if( tender.getGatheredGrossMass() >= tender.getNeededGrossMass() || tender.getGatheredGrossMass() < (tender.getNeededGrossMass() + tender.getNeededGrossMass() / 5 ) ) {
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
		return repository.findByTypeOrLocation_CountyAndActiveTrue(requestDto.getCategoryType(), requestDto.getCounty());
	}

	public void findTenderByIdAndCloseIt(TenderCloseRequestDto requestDto) {
		Optional<TenderDao> tender = repository.findById(requestDto.getTenderId());
		tender.ifPresent(tenderDao -> tenderDao.setActive(false));
	}
}
