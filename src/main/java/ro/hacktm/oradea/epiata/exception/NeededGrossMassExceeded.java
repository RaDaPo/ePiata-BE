package ro.hacktm.oradea.epiata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class NeededGrossMassExceeded extends RuntimeException {

	public NeededGrossMassExceeded(Integer mass) {
		super("Remaining need for this tender is maximum: " + mass +"kg");
	}
}
