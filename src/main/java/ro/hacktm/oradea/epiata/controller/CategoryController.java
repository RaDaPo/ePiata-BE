package ro.hacktm.oradea.epiata.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.hacktm.oradea.epiata.api.CategoryApi;
import ro.hacktm.oradea.epiata.model.entity.CategoryType;
import ro.hacktm.oradea.epiata.service.CategoryService;

import static ro.hacktm.oradea.epiata.utility.Utility.getResponseEntityOk;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryController implements CategoryApi {

	private final CategoryService categoryService;

	@Override
	public ResponseEntity getCategories() {
		return getResponseEntityOk(categoryService.getAllCategories());
	}

	@Override
	public ResponseEntity getCategoryById(Long id) {
		return getResponseEntityOk(categoryService.getCategoriesById(id));
	}

	@Override
	public ResponseEntity getCategoriesByType(CategoryType type) {
		return getResponseEntityOk(categoryService.getCategoriesByType(type));
	}
}
