package ro.hacktm.oradea.epiata.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.hacktm.oradea.epiata.api.TenderApi;
import ro.hacktm.oradea.epiata.model.dto.*;
import ro.hacktm.oradea.epiata.repository.TenderAttendeesRepository;
import ro.hacktm.oradea.epiata.service.TenderService;
import ro.hacktm.oradea.epiata.service.UserService;

import static ro.hacktm.oradea.epiata.utility.Utility.getResponseEntityOk;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TenderController implements TenderApi {

	private final TenderService tenderService;
	private final UserService userService;
	private final TenderAttendeesRepository tenderAttendeesRepository;

	public ResponseEntity getAllUsers() {
		return getResponseEntityOk(tenderService.getAllTenders());
	}

	public ResponseEntity addTender(TenderAddRequest tenderRequestDto) {
		return getResponseEntityOk(tenderService.addNewTender(tenderRequestDto));
	}

	public ResponseEntity addUsersToTender(TenderAddUsersRequestDto tenderRequestDto) {
		return getResponseEntityOk(tenderService.addAttendeesToTender(tenderRequestDto));
	}

	public ResponseEntity acceptUser(TenderAcceptUser acceptUser) {
		tenderService.acceptAttendeeToTender(acceptUser);
		return getResponseEntityOk();
	}

	public ResponseEntity declineUser(TenderAcceptUser acceptUser) {
		tenderService.declineAttendeeFromTender(acceptUser);
		return getResponseEntityOk();
	}

	public ResponseEntity findTendersByDescriptionContaining(String searchPhrase) {
		return getResponseEntityOk(tenderService.findByDescriptionContaining(searchPhrase));
	}

	public ResponseEntity findAllByActiveTrueAndCountyAndCategory(TenderFilteredRequestDto requestDto) {
		return getResponseEntityOk(tenderService.findByCategoryTypeAndCounty(requestDto));
	}

	public void closetTender(TenderCloseRequestDto requestDto) {
		tenderService.findTenderByIdAndCloseIt(requestDto);
	}

}
