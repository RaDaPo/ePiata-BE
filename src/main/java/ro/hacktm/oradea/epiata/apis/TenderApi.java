package ro.hacktm.oradea.epiata.apis;

import org.springframework.web.bind.annotation.*;
import ro.hacktm.oradea.epiata.model.dto.TenderDto;
import ro.hacktm.oradea.epiata.model.entity.TenderDao;

import java.util.List;

@RequestMapping(path = "/api/tenders")
public interface TenderApi {

	@GetMapping
	List<TenderDto> getAllUsers();

	@PostMapping
	TenderDao addTender(@RequestBody TenderDto tenderDto);

	@PutMapping(value = "/{id}")
	TenderDao updateTender(@RequestBody TenderDto tenderDto, @PathVariable(name = "id") Long id);
}
