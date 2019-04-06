package ro.hacktm.oradea.epiata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.hacktm.oradea.epiata.model.dto.TenderAcceptUser;
import ro.hacktm.oradea.epiata.model.dto.TenderAddUsersRequestDto;
import ro.hacktm.oradea.epiata.model.dto.TenderRequestDto;
import ro.hacktm.oradea.epiata.model.dto.TenderResponseDto;
import ro.hacktm.oradea.epiata.model.entity.AcceptedUser;
import ro.hacktm.oradea.epiata.model.entity.TenderAttendee;
import ro.hacktm.oradea.epiata.model.entity.TenderDao;
import ro.hacktm.oradea.epiata.model.entity.UserDao;
import ro.hacktm.oradea.epiata.repository.TenderAttendeesRepository;
import ro.hacktm.oradea.epiata.repository.TenderRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TenderService {

	private final TenderRepository repository;
	private final UserService userService;
	private final TenderAttendeesRepository tenderAttendeesRepository;

	public List<TenderResponseDto> getAllTenders() {
		List<TenderDao> entity = repository.findAll();
		return entity.stream().map(TenderDao::toDto).collect(Collectors.toList());
	}

	public TenderResponseDto addNewTender(TenderRequestDto tenderRequestDto) {
		TenderDao tender = new TenderDao();
		BeanUtils.copyProperties(tenderRequestDto, tender);
		Optional<UserDao> owner = userService.getUserById(tenderRequestDto.getOwnerId());
		owner.ifPresent(userDao -> tender.setOwner(userDao.toOwnerDao()));
		save(tender);
		return tender.toDto();
	}

	public TenderDao addAttendeesToTender(TenderAddUsersRequestDto tenderRequestDto) {
		Optional<TenderDao> tender = getTenderById(tenderRequestDto.getTenderId());
		if (tender.isPresent() && (tender.get().getNeededGrossMass() - tender.get().getGatheredGrossMass()) > tenderRequestDto.getParticipationMass()) {
			Optional<UserDao> user = userService.getUserById(tenderRequestDto.getUserId());
			if (user.isPresent()) {
				tender.get().getUsers().add(user.get());
				tender.get().setGatheredGrossMass(tender.get().getGatheredGrossMass() + tenderRequestDto.getParticipationMass());
				TenderAttendee tenderAttendee = new TenderAttendee();
				tenderAttendee.setUserId(user.get().getId());
				tenderAttendee.setParticipationMass(tenderRequestDto.getParticipationMass());
				tender.get().getTenderAttendees().add(tenderAttendee);
				save(tender.get());
			}
		}
		return tender.orElseThrow(RuntimeException::new);
	}

	public void acceptAttendeeToTender(TenderAcceptUser acceptUser) {
		Optional<TenderDao> tender = getTenderById(acceptUser.getTenderId());
		if (tender.isPresent()) {
			if (tender.get().getAcceptedUserIds() != null) {
				AcceptedUser acceptedUser = new AcceptedUser();
				acceptedUser.setUserId(acceptUser.getUserId());
				tender.get().getAcceptedUserIds().add(acceptedUser);
				TenderAttendee tenderAttendee = tenderAttendeesRepository.findByUserId(acceptUser.getUserId());
				tenderAttendee.setAccepted(true);
				tenderAttendeesRepository.save(tenderAttendee);
				tender.get().setGatheredGrossMass(tender.get().getGatheredGrossMass() + tenderAttendee.getParticipationMass());
				save(tender.get());
			} else {
				AcceptedUser acceptedUser = new AcceptedUser();
				acceptedUser.setUserId(acceptUser.getUserId());
				tender.get().setAcceptedUserIds(Collections.singletonList(acceptedUser));
				TenderAttendee tenderAttendee = tenderAttendeesRepository.findByUserId(acceptUser.getUserId());
				tenderAttendee.setAccepted(true);
				tenderAttendeesRepository.save(tenderAttendee);
				tender.get().setGatheredGrossMass(tender.get().getGatheredGrossMass() + tenderAttendee.getParticipationMass());
				save(tender.get());
			}
		}
	}

	public void declineAttendeeFromTender(TenderAcceptUser acceptUser) {
		Optional<TenderDao> tender = getTenderById(acceptUser.getTenderId());
		if (tender.isPresent()) {
			TenderAttendee tenderAttendee = tenderAttendeesRepository.findByUserId(acceptUser.getUserId());
			tenderAttendee.setAccepted(true);
			tender.get().getTenderAttendees().remove(tenderAttendee);
			Optional<UserDao> userDao = userService.getUserById(acceptUser.getUserId());
			userDao.ifPresent(dao -> tender.get().getUsers().remove(dao));
			tenderAttendeesRepository.delete(tenderAttendee);
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
		return repository.findByDescriptionContaining(searchPhrase);
	}
}
