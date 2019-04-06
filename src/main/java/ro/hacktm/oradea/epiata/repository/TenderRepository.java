package ro.hacktm.oradea.epiata.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ro.hacktm.oradea.epiata.model.entity.CategoryType;
import ro.hacktm.oradea.epiata.model.entity.TenderDao;

import java.util.List;

@Repository
public interface TenderRepository extends PagingAndSortingRepository<TenderDao, Long> {
	List<TenderDao> findAll();

	List<TenderDao> findAllByActiveTrue();

	List<TenderDao> findByDescriptionOrTitleContainingAndActiveTrue(String searchPhrase);

	List<TenderDao> findByTypeOrLocation_CountyAndActiveTrue(CategoryType categoryType, String county);

}
