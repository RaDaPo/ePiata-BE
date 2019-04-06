package ro.hacktm.oradea.epiata.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ro.hacktm.oradea.epiata.model.entity.TenderAttendee;

import java.util.List;

@Repository
public interface TenderAttendeesRepository extends PagingAndSortingRepository<TenderAttendee, Long> {

	TenderAttendee findByUserId(Long userId);

}
