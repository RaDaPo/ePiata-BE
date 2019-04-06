package ro.hacktm.oradea.epiata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.hacktm.oradea.epiata.exceptions.CategoryNotFoundException;
import ro.hacktm.oradea.epiata.model.dto.CategoryDto;
import ro.hacktm.oradea.epiata.model.entity.CategoryDao;
import ro.hacktm.oradea.epiata.model.entity.enums.CategoryType;
import ro.hacktm.oradea.epiata.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryService {

	private final CategoryRepository categoryRepository;

	public List<CategoryDto> getAllCategories() {
		return categoryRepository.findAll()
				.stream()
				.map(CategoryDao::toDto)
				.collect(Collectors.toList());
	}

	public CategoryDto getCategoriesById(Long id) {
		Optional<CategoryDao> category = categoryRepository.findById(id);

		if (category.isPresent())
			return category.get().toDto();

		throw new CategoryNotFoundException(id);
	}

	public List<CategoryDto> getCategoriesByType(CategoryType type) {
		return categoryRepository.findAllByType(type)
				.stream()
				.map(CategoryDao::toDto)
				.collect(Collectors.toList());
	}
}
