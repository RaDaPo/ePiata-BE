package ro.hacktm.oradea.epiata.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ro.hacktm.oradea.epiata.apis.OfferApi;
import ro.hacktm.oradea.epiata.model.dto.OfferDto;
import ro.hacktm.oradea.epiata.service.OfferService;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OfferController implements OfferApi {

    private final OfferService service;

    public List<OfferDto> getAllOffers() {
        return service.getAllOffers();
    }

    public OfferDto getOffer(Long id) {
        return service.getOffer(id);
    }

    public void deleteOffer(Long id) {
        service.deleteOffer(id);
    }

    public void updateOffer(OfferDto dto) {
        service.updateOffer(dto);
    }

    public void createOffer(OfferDto dto) {
        service.createOffer(dto);
    }
}
