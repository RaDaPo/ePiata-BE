package ro.hacktm.oradea.epiata.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ro.hacktm.oradea.epiata.model.entity.OfferDao;

import java.util.List;

@Repository
public interface OfferRepository extends PagingAndSortingRepository<OfferDao, Long> {
	List<OfferDao> findByCategoryAndNameAndLocation_Address(Long categoryId, String name, String county);

	List<OfferDao> findAll();

}
