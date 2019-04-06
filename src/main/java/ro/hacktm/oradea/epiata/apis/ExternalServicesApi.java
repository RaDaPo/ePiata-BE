package ro.hacktm.oradea.epiata.apis;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.hacktm.oradea.epiata.model.external_services.DistanceDto;

@CrossOrigin
@RequestMapping(path = "/api")
public interface ExternalServicesApi {

	@GetMapping(path = "/distance/{start_location}/{end_location}")
	DistanceDto getDistanceBetweenLocations(@PathVariable(name = "start_location") String startLocation,
	                                        @PathVariable(name = "end_location") String endLocation);

}
