package ro.hacktm.oradea.epiata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends RuntimeException {

	private static final String MESSAGE = "Category not found!";

	public CategoryNotFoundException(Long id) {
		super(MESSAGE + " id: " + id);
	}

	public CategoryNotFoundException(String name) {
		super(MESSAGE + " name: " + name);
	}
}
