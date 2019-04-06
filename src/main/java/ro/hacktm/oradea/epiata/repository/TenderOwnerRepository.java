package ro.hacktm.oradea.epiata.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ro.hacktm.oradea.epiata.model.entity.TenderOwner;

@Repository
public interface TenderOwnerRepository extends PagingAndSortingRepository<TenderOwner, Long> {

}
