package ro.hacktm.oradea.epiata.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ro.hacktm.oradea.epiata.model.entity.Category;
import ro.hacktm.oradea.epiata.model.entity.CategoryType;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
	List<Category> findAll();

	List<Category> findAllByType(CategoryType type);

	Optional<Category> findByName(String name);
}
