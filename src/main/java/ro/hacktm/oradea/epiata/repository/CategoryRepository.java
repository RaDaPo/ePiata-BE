package ro.hacktm.oradea.epiata.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ro.hacktm.oradea.epiata.model.entity.CategoryDao;
import ro.hacktm.oradea.epiata.model.entity.CategoryType;

import java.util.List;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<CategoryDao, Long> {
	List<CategoryDao> findAll();

	List<CategoryDao> findAllByType(CategoryType type);
}
