package ro.hacktm.oradea.epiata.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.hacktm.oradea.epiata.model.dto.TenderAcceptUser;
import ro.hacktm.oradea.epiata.model.dto.TenderAddRequest;
import ro.hacktm.oradea.epiata.model.dto.TenderAddUsersRequestDto;
import ro.hacktm.oradea.epiata.model.dto.TenderFilteredRequestDto;

@CrossOrigin
@RequestMapping(path = "/api/tenders")
public interface TenderApi {

	@GetMapping
	ResponseEntity getAllUsers();

	@PostMapping
	ResponseEntity addTender(@RequestBody TenderAddRequest tenderRequestDto);

	@PutMapping
	ResponseEntity addUsersToTender(@RequestBody TenderAddUsersRequestDto tenderRequestDto);

	@PutMapping(value = "/accept")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	ResponseEntity acceptUser(@RequestBody TenderAcceptUser acceptUser);

	@PutMapping(value = "/decline")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	ResponseEntity declineUser(@RequestBody TenderAcceptUser acceptUser);

	@GetMapping(value = "/search/by-phrase")
	ResponseEntity findTendersByDescriptionContaining(@RequestParam(name = "searchPhrase") String searchPhrase);

	@PostMapping(value = "/search/by-category")
	ResponseEntity findAllByActiveTrueAndCountyAndCategory(@RequestBody TenderFilteredRequestDto requestDto);
}
