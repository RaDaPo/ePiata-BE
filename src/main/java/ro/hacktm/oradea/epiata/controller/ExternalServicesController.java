package ro.hacktm.oradea.epiata.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ro.hacktm.oradea.epiata.apis.ExternalServicesApi;
import ro.hacktm.oradea.epiata.model.external_services.DistanceDto;
import ro.hacktm.oradea.epiata.service.ExternalServices;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExternalServicesController implements ExternalServicesApi {

    private final ExternalServices externalServices;

    public DistanceDto getDistanceBetweenLocations(String startLocation, String endLocation) {
        return externalServices.getDistanceBetweenLocations(startLocation, endLocation);
    }
}
