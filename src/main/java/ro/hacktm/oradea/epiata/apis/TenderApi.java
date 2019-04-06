package ro.hacktm.oradea.epiata.apis;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.hacktm.oradea.epiata.model.dto.*;
import ro.hacktm.oradea.epiata.model.entity.TenderDao;

import java.util.List;

@CrossOrigin
@RequestMapping(path = "/api/tenders")
public interface TenderApi {

	@GetMapping
	List<TenderResponseDto> getAllUsers();

	@PostMapping
	TenderResponseDto addTender(@RequestBody TenderAddRequest tenderRequestDto);

	@PutMapping
	TenderDao addUsersToTender(@RequestBody TenderAddUsersRequestDto tenderRequestDto);

	@PutMapping(value = "/accept")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void acceptUser(@RequestBody TenderAcceptUser acceptUser);

	@PutMapping(value = "/decline")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void declineUser(@RequestBody TenderAcceptUser acceptUser);

	@GetMapping(value = "/search")
	List<TenderDao> findTendersByDescriptionContaining(@RequestParam(name = "searchPhrase") String searchPhrase);
}
