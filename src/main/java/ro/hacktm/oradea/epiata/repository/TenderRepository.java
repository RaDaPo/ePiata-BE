package ro.hacktm.oradea.epiata.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;
import ro.hacktm.oradea.epiata.model.entity.Tender;

import java.util.List;

@Repository
public interface TenderRepository extends PagingAndSortingRepository<Tender, Long> {
    List<Tender> findAll();

}
