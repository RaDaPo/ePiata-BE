package ro.hacktm.oradea.epiata.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ro.hacktm.oradea.epiata.model.entity.Offer;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends PagingAndSortingRepository<Offer, Long> {
	List<Offer> findAll();
	Optional<Offer> findOfferByCategory(Long category);
	List<Offer> findByCategoryOrNameOrLocation_County(Long category, String name, String county);
}
