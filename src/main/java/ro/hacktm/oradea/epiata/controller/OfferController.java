package ro.hacktm.oradea.epiata.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.hacktm.oradea.epiata.api.OfferApi;
import ro.hacktm.oradea.epiata.model.dto.GetAllOffersRequest;
import ro.hacktm.oradea.epiata.model.dto.OfferDto;
import ro.hacktm.oradea.epiata.service.OfferService;

import static ro.hacktm.oradea.epiata.utility.Utility.getResponseEntityOk;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OfferController implements OfferApi {

	private final OfferService service;

	public ResponseEntity getAllOffers(GetAllOffersRequest request) {
		return getResponseEntityOk(service.getAllOffers(request));
	}

	public ResponseEntity getOffer(Long id) {
		return getResponseEntityOk(service.getOffer(id));
	}

	public ResponseEntity deleteOffer(Long id) {
		service.deleteOffer(id);
		return getResponseEntityOk();
	}

	public ResponseEntity updateOffer(OfferDto dto) {
		service.updateOffer(dto);
		return getResponseEntityOk();
	}

	public ResponseEntity createOffer(OfferDto dto) {
		service.createOffer(dto);
		return getResponseEntityOk();
	}
}
