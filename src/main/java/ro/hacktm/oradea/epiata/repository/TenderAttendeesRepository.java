package ro.hacktm.oradea.epiata.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ro.hacktm.oradea.epiata.model.entity.TenderAttendee;

import java.util.Optional;

@Repository
public interface TenderAttendeesRepository extends PagingAndSortingRepository<TenderAttendee, Long> {

	Optional<TenderAttendee> findByUserId(Long userId);

}
