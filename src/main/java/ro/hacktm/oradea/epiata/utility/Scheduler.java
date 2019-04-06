package ro.hacktm.oradea.epiata.utility;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ro.hacktm.oradea.epiata.model.entity.OfferDao;
import ro.hacktm.oradea.epiata.model.entity.TenderDao;
import ro.hacktm.oradea.epiata.repository.OfferRepository;
import ro.hacktm.oradea.epiata.repository.TenderRepository;

import java.util.Date;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Scheduler {
	private final OfferRepository repository;
	private final TenderRepository tenderRepository;

	//	@Scheduled(cron = "0 0 9 1 * ?")
	@Scheduled(fixedDelay = 10000)
	private void create() {
		validateOffer();
	}

	private void validateOffer() {
		repository.findAll()
				.stream()
				.filter(Objects::nonNull)
				.forEach(this::validateOfferDates);

		tenderRepository.findAll()
				.stream()
				.filter(Objects::nonNull)
				.forEach(this::validateTenderDates);
		log.info("Offers and Tenders have been updated at: " + new Date());
	}

	private void validateOfferDates(OfferDao offerDao) {
		if (offerDao.getEndDate().compareTo(new Date()) < 0) {
			offerDao.setActive(false);
			repository.save(offerDao);
		}
	}

	private void validateTenderDates(TenderDao offerDao) {
		if (offerDao.getEndDate().compareTo(new Date()) < 0) {
			offerDao.setActive(false);
			offerDao.setStatus(false);
			tenderRepository.save(offerDao);
		}
	}

}
