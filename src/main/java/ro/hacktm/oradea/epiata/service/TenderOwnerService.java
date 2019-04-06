package ro.hacktm.oradea.epiata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.hacktm.oradea.epiata.model.entity.TenderDao;
import ro.hacktm.oradea.epiata.repository.TenderRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TenderOwnerService {

	private final TenderRepository repository;

	public Optional<TenderDao> getTenderById(Long id) {
		return repository.findById(id);
	}
}
