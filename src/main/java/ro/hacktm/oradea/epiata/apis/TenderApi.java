package ro.hacktm.oradea.epiata.apis;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.hacktm.oradea.epiata.model.dto.TenderAcceptUser;
import ro.hacktm.oradea.epiata.model.dto.TenderAddUsersRequestDto;
import ro.hacktm.oradea.epiata.model.dto.TenderRequestDto;
import ro.hacktm.oradea.epiata.model.dto.TenderResponseDto;
import ro.hacktm.oradea.epiata.model.entity.TenderDao;

import java.util.List;

@RequestMapping(path = "/api/tenders")
public interface TenderApi {

	@GetMapping
	List<TenderResponseDto> getAllUsers();

	@PostMapping
	TenderResponseDto addTender(@RequestBody TenderRequestDto tenderRequestDto);

	@PutMapping
	TenderDao addUsersToTender(@RequestBody TenderAddUsersRequestDto tenderRequestDto);

	@PutMapping(value = "/accept")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void acceptUser(@RequestBody TenderAcceptUser acceptUser);

	@PutMapping(value = "/decline")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void declineUser(@RequestBody TenderAcceptUser acceptUser);
}
