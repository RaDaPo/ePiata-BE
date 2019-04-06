package ro.hacktm.oradea.epiata.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends RuntimeException {

	public CategoryNotFoundException(Long id) {
		super("Category not found! id: " + id);
	}
}
