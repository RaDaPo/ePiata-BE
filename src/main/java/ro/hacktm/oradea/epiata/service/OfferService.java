package ro.hacktm.oradea.epiata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ro.hacktm.oradea.epiata.exception.OfferNotFoundException;
import ro.hacktm.oradea.epiata.model.dto.CategoryDto;
import ro.hacktm.oradea.epiata.model.dto.FilteredOffersRequest;
import ro.hacktm.oradea.epiata.model.dto.OfferDto;
import ro.hacktm.oradea.epiata.model.entity.Offer;
import ro.hacktm.oradea.epiata.repository.OfferRepository;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OfferService {

    private final OfferRepository repository;
    private final CategoryService categoryService;
    private final ExternalServices services;

    public List<OfferDto> getAllOffers() {
        return repository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(this::getOfferDto)
                .collect(Collectors.toList());
    }

    private OfferDto getOfferDto(Offer offer) {
        OfferDto dto = offer.toDto();
        try {
            dto.getLocation().setTimeToDestination(services.getDistanceBetweenLocations(dto.getLocation().getCity()).getTimeInMin());
            dto.getLocation().setDistance(services.getDistanceBetweenLocations(dto.getLocation().getCity()).getDistanceInKm());
        } catch (Exception ignored) {
        }
        return dto;
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
            categoryId = request.getCategory();

        if (request.getLocation() != null)
            county = request.getLocation().getCounty();

        return repository.findAll(new Specification<Offer>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Offer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                Path<Object> offer = root.get("offer");

                if (request.getSearchTerm() != null)
                    predicates.add(criteriaBuilder.equal(offer.get("name"), request.getSearchTerm()));

                if (request.getCategory() != null)
                    predicates.add(criteriaBuilder.equal(offer.get("category").get("name"), request.getCategory()));

                if (request.getLocation() != null) {
                    predicates.add(criteriaBuilder.equal(offer.get("location").get("county"), request.getLocation().getCounty()));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        });
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

        CategoryDto category = categoryService.getCategoriesById(dto.getCategory());
        dao.setCategory(category.getId());
        repository.save(dao);
    }

    public void updateOffer(OfferDto offer) {
        Offer dao = new Offer();
        BeanUtils.copyProperties(offer, dao);
        repository.save(dao);
    }
}
