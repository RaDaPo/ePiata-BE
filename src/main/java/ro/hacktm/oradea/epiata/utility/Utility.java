package ro.hacktm.oradea.epiata.utility;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@UtilityClass
public final class Utility {

	public static ResponseEntity getResponseEntityOk() {
		return new ResponseEntity(HttpStatus.OK);
	}

	public static ResponseEntity<Object> getResponseEntityOk(Object entity) {
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
}
