package ro.hacktm.oradea.epiata.apis;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.hacktm.oradea.epiata.model.dto.OfferDto;

import java.util.List;

@RequestMapping(path = "/api/offer")
public interface OfferApi {

    @GetMapping(path = "/all")
    List<OfferDto> getAllOffers();

    @GetMapping(path = "/{id}")
    OfferDto getOffer(@PathVariable Long id);

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void deleteOffer(@PathVariable Long id);

    @PutMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void updateOffer(@RequestBody OfferDto dto);

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void createOffer(@RequestBody OfferDto dto);
}
