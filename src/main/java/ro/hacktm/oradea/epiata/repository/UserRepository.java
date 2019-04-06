package ro.hacktm.oradea.epiata.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ro.hacktm.oradea.epiata.model.entity.UserDao;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserDao, Long> {
    List<UserDao> findAll();
}
