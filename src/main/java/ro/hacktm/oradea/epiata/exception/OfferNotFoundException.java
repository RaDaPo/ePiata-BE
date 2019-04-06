package ro.hacktm.oradea.epiata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static java.lang.String.format;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OfferNotFoundException extends RuntimeException {

	private static final String MESSAGE = "Offer id: %d not found!";

	public OfferNotFoundException(Long id) {
		super(format(MESSAGE, id));
	}
}
