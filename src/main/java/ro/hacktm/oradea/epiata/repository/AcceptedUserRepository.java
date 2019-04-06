package ro.hacktm.oradea.epiata.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ro.hacktm.oradea.epiata.model.entity.AcceptedUser;

public interface AcceptedUserRepository extends PagingAndSortingRepository<AcceptedUser, Long> {
}
