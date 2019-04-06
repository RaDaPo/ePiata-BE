package ro.hacktm.oradea.epiata.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.hacktm.oradea.epiata.apis.CategoryApi;
import ro.hacktm.oradea.epiata.model.entity.enums.CategoryType;
import ro.hacktm.oradea.epiata.service.CategoryService;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryController implements CategoryApi {

	private final CategoryService categoryService;

	@Override
	public ResponseEntity getCategories() {
		return getResponseEntity(categoryService.getAllCategories());
	}

	@Override
	public ResponseEntity getCategoryById(Long id) {
		return getResponseEntity(categoryService.getCategoriesById(id));
	}

	@Override
	public ResponseEntity getCategoriesByType(CategoryType type) {
		return getResponseEntity(categoryService.getCategoriesByType(type));
	}

	private ResponseEntity<Object> getResponseEntity(Object entity) {
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
}
