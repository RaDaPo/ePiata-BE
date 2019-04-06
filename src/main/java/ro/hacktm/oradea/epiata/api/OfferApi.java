package ro.hacktm.oradea.epiata.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.hacktm.oradea.epiata.model.dto.FilteredOffersRequest;
import ro.hacktm.oradea.epiata.model.dto.OfferDto;

@CrossOrigin
@RequestMapping(path = "/api/offers")
public interface OfferApi {

	@GetMapping
	ResponseEntity getAllOffers();

	@PostMapping(path = "/all")
	ResponseEntity getAllFilteredOffers(@RequestBody FilteredOffersRequest request);

	@GetMapping(path = "/{id}")
	ResponseEntity getOffer(@PathVariable Long id);

	@DeleteMapping(path = "/{id}")
	ResponseEntity deleteOffer(@PathVariable Long id);

	@PutMapping
	ResponseEntity updateOffer(@RequestBody OfferDto dto);

	@PostMapping
	ResponseEntity createOffer(@RequestBody OfferDto dto);
}
