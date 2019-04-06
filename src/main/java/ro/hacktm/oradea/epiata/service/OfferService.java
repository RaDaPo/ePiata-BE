package ro.hacktm.oradea.epiata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.hacktm.oradea.epiata.exception.OfferNotFoundException;
import ro.hacktm.oradea.epiata.model.dto.GetAllOffersRequest;
import ro.hacktm.oradea.epiata.model.dto.OfferDto;
import ro.hacktm.oradea.epiata.model.entity.OfferDao;
import ro.hacktm.oradea.epiata.repository.OfferRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OfferService {

    private final OfferRepository repository;

    public List<OfferDto> getAllOffers(GetAllOffersRequest request) {
        List<OfferDao> offersList = repository
                .findByCategoryAndNameAndCounty(request.getCategory(), request.getSearchTerm(), request.getCounty());
        return offersList
                .stream()
                .filter(Objects::nonNull)
                .map(OfferDao::toDto)
                .collect(Collectors.toList());
    }

    public OfferDto getOffer(Long id) {
        OfferDto dto = new OfferDto();
        Optional<OfferDao> dao = repository.findById(id);

        if (dao.isPresent()) {
            BeanUtils.copyProperties(dao.get(), dto);
            return dto;
        }

        throw new OfferNotFoundException(id);
    }

    public void deleteOffer(Long id) {
        Optional<OfferDao> dao = repository.findById(id);
        if (dao.isPresent())
            repository.delete(dao.get());
        else
            throw new OfferNotFoundException(id);
    }

    public void createOffer(OfferDto dto) {
        OfferDao dao = new OfferDao();
        BeanUtils.copyProperties(dto, dao);
        repository.save(dao);
    }

    public void updateOffer(OfferDto offer) {
        OfferDao dao = new OfferDao();
        BeanUtils.copyProperties(offer, dao);
        repository.save(dao);
    }
}
