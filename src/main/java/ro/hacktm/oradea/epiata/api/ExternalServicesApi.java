package ro.hacktm.oradea.epiata.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@RequestMapping(path = "/api")
public interface ExternalServicesApi {

	@GetMapping(path = "/distance/{start_location}/{end_location}")
	ResponseEntity getDistanceBetweenLocations(@PathVariable(name = "start_location") String startLocation,
	                                           @PathVariable(name = "end_location") String endLocation);

}
