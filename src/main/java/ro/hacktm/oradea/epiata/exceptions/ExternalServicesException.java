package ro.hacktm.oradea.epiata.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExternalServicesException extends RuntimeException {
	public ExternalServicesException(String m) {
		super(m);
	}
}
