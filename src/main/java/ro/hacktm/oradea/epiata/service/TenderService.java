package ro.hacktm.oradea.epiata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.hacktm.oradea.epiata.model.dto.TenderDto;
import ro.hacktm.oradea.epiata.model.entity.TenderDao;
import ro.hacktm.oradea.epiata.repository.TenderRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TenderService {

	private final TenderRepository repository;

	public List<TenderDto> getAllTenders() {
		List<TenderDao> entity = repository.findAll();
		return entity.stream().map(TenderDao::toDto).collect(Collectors.toList());
	}

	public void save(TenderDao tender) {
		repository.save(tender);
	}

	public Optional<TenderDao> getTenderById(Long id) {
		return repository.findById(id);
	}
}
