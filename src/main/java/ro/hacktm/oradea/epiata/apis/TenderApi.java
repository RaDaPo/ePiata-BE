package ro.hacktm.oradea.epiata.apis;

import org.springframework.http.HttpStatus;
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

    @PutMapping(value = "/accept/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void acceptUser(@RequestParam(name = "acceptedUserId") Long userId, @PathVariable(name = "id") Long id);
}
