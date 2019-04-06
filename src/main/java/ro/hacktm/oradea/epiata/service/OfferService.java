package ro.hacktm.oradea.epiata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.hacktm.oradea.epiata.exceptions.OfferException;
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

    public List<OfferDto> getAllOffers() {
        List<OfferDao> offersList = repository.findAll();
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
        } else {
            throw new OfferException("Offer not found");
        }
    }

    public void deleteOffer(Long id) {
        Optional<OfferDao> dao = repository.findById(id);
        if (dao.isPresent()) {
            repository.delete(dao.get());
        } else {
            throw new OfferException("Offer cannot be deleted");
        }
    }

    public void createOffer(OfferDto dto) {
        OfferDao dao = new OfferDao();
        BeanUtils.copyProperties(dto, dao);
        repository.save(dao);
    }

    public void updateOffer(OfferDto dto) {
        if (dto != null) {
            OfferDao dao = new OfferDao();
            BeanUtils.copyProperties(dto, dao);
            repository.save(dao);
        } else {
            throw new OfferException("Offer cannot be updated");
        }
    }
}
