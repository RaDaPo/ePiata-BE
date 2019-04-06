package ro.hacktm.oradea.epiata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.hacktm.oradea.epiata.exception.OfferNotFoundException;
import ro.hacktm.oradea.epiata.model.dto.FilteredOffersRequest;
import ro.hacktm.oradea.epiata.model.dto.OfferDto;
import ro.hacktm.oradea.epiata.model.entity.Offer;
import ro.hacktm.oradea.epiata.repository.OfferRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OfferService {

	private final OfferRepository repository;
	private final CategoryService categoryService;

	public List<OfferDto> getAllOffers() {
		return repository.findAll()
				.stream()
				.filter(Objects::nonNull)
				.map(Offer::toDto)
				.collect(Collectors.toList());
	}

	public List<OfferDto> getAllOffers(FilteredOffersRequest request) {

		return getFilteredOffers(request)
				.stream()
				.filter(Objects::nonNull)
				.map(Offer::toDto)
				.collect(Collectors.toList());
	}

	private List<Offer> getFilteredOffers(FilteredOffersRequest request) {
		Long categoryId = null;
		String name = request.getSearchTerm();
		String county = null;

		if (request.getCategory() != null)
			categoryId = categoryService.getCategoryByName(request.getCategory()).getId();

		if (request.getLocation() != null)
			county = request.getLocation().getCounty();

		return repository
				.findByCategoryOrNameOrLocation_County(categoryId, name, county);
	}

	public OfferDto getOffer(Long id) {
		OfferDto dto = new OfferDto();
		Optional<Offer> dao = repository.findById(id);

		if (dao.isPresent()) {
			BeanUtils.copyProperties(dao.get(), dto);
			return dto;
		}

		throw new OfferNotFoundException(id);
	}

	public void deleteOffer(Long id) {
		Optional<Offer> dao = repository.findById(id);
		if (dao.isPresent())
			repository.delete(dao.get());
		else
			throw new OfferNotFoundException(id);
	}

	public void createOffer(OfferDto dto) {
		Offer dao = new Offer();
		BeanUtils.copyProperties(dto, dao);
		repository.save(dao);
	}

	public void updateOffer(OfferDto offer) {
		Offer dao = new Offer();
		BeanUtils.copyProperties(offer, dao);
		repository.save(dao);
	}
}
