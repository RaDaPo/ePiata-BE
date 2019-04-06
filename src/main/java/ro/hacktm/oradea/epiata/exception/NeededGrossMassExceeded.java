package ro.hacktm.oradea.epiata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NeededGrossMassExceeded extends RuntimeException {

	public NeededGrossMassExceeded() {
		super("The need for this tender has been exceeded or the tender does not exists");
	}
}
