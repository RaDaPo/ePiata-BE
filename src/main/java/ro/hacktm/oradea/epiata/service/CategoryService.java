package ro.hacktm.oradea.epiata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.hacktm.oradea.epiata.exception.CategoryNotFoundException;
import ro.hacktm.oradea.epiata.model.dto.CategoryDto;
import ro.hacktm.oradea.epiata.model.entity.Category;
import ro.hacktm.oradea.epiata.model.entity.CategoryType;
import ro.hacktm.oradea.epiata.repository.CategoryRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryService {

	private final CategoryRepository categoryRepository;

	public List<CategoryDto> getAllCategories() {
		return categoryRepository.findAll()
				.stream()
				.filter(Objects::nonNull)
				.map(Category::toDto)
				.collect(Collectors.toList());
	}

	public CategoryDto getCategoriesById(Long id) {
		Optional<Category> category = categoryRepository.findById(id);

		if (category.isPresent())
			return category.get().toDto();

		throw new CategoryNotFoundException(id);
	}

	public List<CategoryDto> getCategoriesByType(CategoryType type) {
		return categoryRepository.findAllByType(type)
				.stream()
				.filter(Objects::nonNull)
				.map(Category::toDto)
				.collect(Collectors.toList());
	}

	CategoryDto getCategoryByName(String name) {

		Optional<Category> category = categoryRepository.findByName(name);

		if (category.isPresent())
			return category.get().toDto();

		throw new CategoryNotFoundException(name);
	}
}
