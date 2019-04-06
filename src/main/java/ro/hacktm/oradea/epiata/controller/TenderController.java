package ro.hacktm.oradea.epiata.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ro.hacktm.oradea.epiata.apis.TenderApi;
import ro.hacktm.oradea.epiata.model.dto.*;
import ro.hacktm.oradea.epiata.model.entity.TenderDao;
import ro.hacktm.oradea.epiata.repository.TenderAttendeesRepository;
import ro.hacktm.oradea.epiata.service.TenderService;
import ro.hacktm.oradea.epiata.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TenderController implements TenderApi {

	private final TenderService tenderService;
	private final UserService userService;
	private final TenderAttendeesRepository tenderAttendeesRepository;

	public List<TenderResponseDto> getAllUsers() {
		return tenderService.getAllTenders();
	}

	public TenderResponseDto addTender(TenderAddRequest tenderRequestDto) {
		return tenderService.addNewTender(tenderRequestDto);
	}

	public TenderDao addUsersToTender(TenderAddUsersRequestDto tenderRequestDto) {
		return tenderService.addAttendeesToTender(tenderRequestDto);
	}

	public void acceptUser(TenderAcceptUser acceptUser) {
		tenderService.acceptAttendeeToTender(acceptUser);
	}

	public void declineUser(TenderAcceptUser acceptUser) {
		tenderService.declineAttendeeFromTender(acceptUser);
	}

	public List<TenderDao> findTendersByDescriptionContaining(String searchPhrase) {
		return tenderService.findByDescriptionContaining(searchPhrase);
	}

}
