package ro.hacktm.oradea.epiata.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ro.hacktm.oradea.epiata.model.entity.Offer;

import java.util.List;

@Repository
public interface OfferRepository extends PagingAndSortingRepository<Offer, Long> {
	List<Offer> findAll();

	List<Offer> findByCategoryOrNameOrLocation_County(Long category, String name, String county);
}
