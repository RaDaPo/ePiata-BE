package ro.hacktm.oradea.epiata.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ro.hacktm.oradea.epiata.apis.TenderApi;
import ro.hacktm.oradea.epiata.model.dto.TenderAcceptUser;
import ro.hacktm.oradea.epiata.model.dto.TenderAddUsersRequestDto;
import ro.hacktm.oradea.epiata.model.dto.TenderRequestDto;
import ro.hacktm.oradea.epiata.model.dto.TenderResponseDto;
import ro.hacktm.oradea.epiata.model.entity.AcceptedUser;
import ro.hacktm.oradea.epiata.model.entity.TenderAttendee;
import ro.hacktm.oradea.epiata.model.entity.TenderDao;
import ro.hacktm.oradea.epiata.model.entity.UserDao;
import ro.hacktm.oradea.epiata.repository.AcceptedUserRepository;
import ro.hacktm.oradea.epiata.repository.TenderAttendeesRepository;
import ro.hacktm.oradea.epiata.repository.TenderRepository;
import ro.hacktm.oradea.epiata.service.TenderService;
import ro.hacktm.oradea.epiata.service.UserService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TenderController implements TenderApi {

	private final TenderService tenderService;
	private final UserService userService;
	private final AcceptedUserRepository acceptedUserRepository;
	private final TenderAttendeesRepository tenderRepository;


	public List<TenderResponseDto> getAllUsers() {
		return tenderService.getAllTenders();
	}

	public TenderResponseDto addTender(TenderRequestDto tenderRequestDto) {
		TenderDao tender = new TenderDao();
		BeanUtils.copyProperties(tenderRequestDto, tender);
		Optional<UserDao> owner = userService.getUserById(tenderRequestDto.getOwnerId());
		owner.ifPresent(userDao -> tender.setOwner(userDao.toOwnerDao()));
		tenderService.save(tender);
		return tender.toDto();
	}

	public TenderDao addUsersToTender(TenderAddUsersRequestDto tenderRequestDto) {
		Optional<TenderDao> tender = tenderService.getTenderById(tenderRequestDto.getTenderId());
		if (tender.isPresent() && (tender.get().getNeededGrossMass() - tender.get().getGatheredGrossMass()) > tenderRequestDto.getParticipationMass()) {
				Optional<UserDao> user = userService.getUserById(tenderRequestDto.getUserId());
				if(user.isPresent()) {
					tender.get().getUsers().add(user.get());
					tender.get().setGatheredGrossMass(tender.get().getGatheredGrossMass() + tenderRequestDto.getParticipationMass());
					TenderAttendee tenderAttendee = new TenderAttendee();
					tenderAttendee.setUserId(user.get().getId());
					tenderAttendee.setParticipationMass(tenderRequestDto.getParticipationMass());
					tender.get().getTenderAttendees().add(tenderAttendee);
					tenderService.save(tender.get());
				}
		}
		return tender.orElseThrow(RuntimeException::new);
	}

	public void acceptUser(TenderAcceptUser acceptUser) {
		Optional<TenderDao> tender = tenderService.getTenderById(acceptUser.getTenderId());
		if (tender.isPresent()) {
			if (tender.get().getAcceptedUserIds() != null) {
				AcceptedUser acceptedUser = new AcceptedUser();
				acceptedUser.setUserId(acceptUser.getUserId());
				tender.get().getAcceptedUserIds().add(acceptedUser);
				TenderAttendee tenderAttendee = tenderRepository.findByUserId(acceptUser.getUserId());
				tenderAttendee.setAccepted(true);
				tenderRepository.save(tenderAttendee);
			} else {
				AcceptedUser acceptedUser = new AcceptedUser();
				acceptedUser.setUserId(acceptUser.getUserId());
				tender.get().setAcceptedUserIds(Collections.singletonList(acceptedUser));
				TenderAttendee tenderAttendee = tenderRepository.findByUserId(acceptUser.getUserId());
				tenderAttendee.setAccepted(true);
				tenderRepository.save(tenderAttendee);
			}
			tenderService.save(tender.get());
		}
	}
}
