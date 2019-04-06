package ro.hacktm.oradea.epiata.apis;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.hacktm.oradea.epiata.model.entity.enums.CategoryType;

@CrossOrigin
@RequestMapping("/api/categories")
public interface CategoryApi {

	@GetMapping
	ResponseEntity getCategories();

	@GetMapping("/id/{id}")
	ResponseEntity getCategoryById(@PathVariable Long id);

	@GetMapping("/type/{type}")
	ResponseEntity getCategoriesByType(@PathVariable CategoryType type);

}
