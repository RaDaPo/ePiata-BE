package ro.hacktm.oradea.epiata.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.hacktm.oradea.epiata.api.ExternalServicesApi;
import ro.hacktm.oradea.epiata.service.ExternalServices;

import static ro.hacktm.oradea.epiata.utility.Utility.getResponseEntityOk;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExternalServicesController implements ExternalServicesApi {

	private final ExternalServices externalServices;

	public ResponseEntity getDistanceBetweenLocations(String startLocation, String endLocation) {
		return getResponseEntityOk(externalServices.getDistanceBetweenLocations(startLocation, endLocation));
	}
}
