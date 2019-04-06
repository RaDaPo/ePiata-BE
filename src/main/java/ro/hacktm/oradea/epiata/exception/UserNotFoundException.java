package ro.hacktm.oradea.epiata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static java.lang.String.format;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	private static final String MESSAGE = "User not found! id: %d";

	public UserNotFoundException(Long id) {
		super(format(MESSAGE, id));
	}
}
