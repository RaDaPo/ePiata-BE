package ro.hacktm.oradea.epiata.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ro.hacktm.oradea.epiata.model.entity.TenderDao;

import java.util.List;

@Repository
public interface TenderRepository extends PagingAndSortingRepository<TenderDao, Long> {
    List<TenderDao> findAll();
}
