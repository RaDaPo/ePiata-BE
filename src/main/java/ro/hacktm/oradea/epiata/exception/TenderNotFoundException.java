package ro.hacktm.oradea.epiata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TenderNotFoundException extends RuntimeException {

	public TenderNotFoundException(Long id) {
		super("Tender with the id of: " + id + " was not found");
	}
}
