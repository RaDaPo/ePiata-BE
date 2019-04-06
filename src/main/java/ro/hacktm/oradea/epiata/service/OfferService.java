package ro.hacktm.oradea.epiata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.hacktm.oradea.epiata.exception.OfferNotFoundException;
import ro.hacktm.oradea.epiata.model.dto.GetAllOffersRequest;
import ro.hacktm.oradea.epiata.model.dto.OfferDto;
import ro.hacktm.oradea.epiata.model.entity.Category;
import ro.hacktm.oradea.epiata.model.entity.Offer;
import ro.hacktm.oradea.epiata.repository.CategoryRepository;
import ro.hacktm.oradea.epiata.repository.OfferRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OfferService {

    private final OfferRepository repository;
    private final CategoryRepository categoryRepository;

    public List<OfferDto> getAllOffers() {
        return repository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(Offer::toDto)
                .collect(Collectors.toList());
    }

    public List<OfferDto> getAllOffers(GetAllOffersRequest request) {

        Long categoryId = null;
        if (request.getCategory() != null) {
            Optional<Category> c = categoryRepository.findByName(request.getCategory());

            if (c.isPresent())
                categoryId = c.get().getId();
        }

        String county = null;

        if (request.getLocation() != null)
            county = request.getLocation().getCounty();

        List<Offer> offersList = repository
                .findByCategoryOrNameOrLocation_County(categoryId, request.getSearchTerm(), county);
        return offersList
                .stream()
                .filter(Objects::nonNull)
                .map(Offer::toDto)
                .collect(Collectors.toList());
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
