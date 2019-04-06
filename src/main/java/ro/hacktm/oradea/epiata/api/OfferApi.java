package ro.hacktm.oradea.epiata.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.hacktm.oradea.epiata.model.dto.GetAllOffersRequest;
import ro.hacktm.oradea.epiata.model.dto.OfferDto;

@RequestMapping(path = "/api/offers")
public interface OfferApi {

	@PostMapping(path = "/all")
	ResponseEntity getAllOffers(@RequestBody GetAllOffersRequest request);

	@GetMapping(path = "/{id}")
	ResponseEntity getOffer(@PathVariable Long id);

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	ResponseEntity deleteOffer(@PathVariable Long id);

	@PutMapping
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	ResponseEntity updateOffer(@RequestBody OfferDto dto);

	@PostMapping
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	ResponseEntity createOffer(@RequestBody OfferDto dto);
}
