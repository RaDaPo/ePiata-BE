package ro.hacktm.oradea.epiata.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ro.hacktm.oradea.epiata.apis.TenderApi;
import ro.hacktm.oradea.epiata.model.dto.TenderDto;
import ro.hacktm.oradea.epiata.model.entity.TenderDao;
import ro.hacktm.oradea.epiata.model.entity.UserDao;
import ro.hacktm.oradea.epiata.service.TenderService;
import ro.hacktm.oradea.epiata.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TenderController implements TenderApi {

	private final TenderService tenderService;
	private final UserService userService;

	public List<TenderDto> getAllUsers() {
		return tenderService.getAllTenders();
	}

	public TenderDao addTender(TenderDto tenderDto) {
		TenderDao tender = new TenderDao();
		BeanUtils.copyProperties(tenderDto, tender);
		tenderService.save(tender);
		return tender;
	}

	public TenderDao updateTender(TenderDto tenderDto, Long id) {
		Optional<TenderDao> tender = tenderService.getTenderById(id);
		if (tender.isPresent()) {
			Optional<UserDao> user = userService.getUserById(tenderDto.getUserId());
			user.ifPresent(value -> tender.get().getUsers().add(value));
			tenderService.save(tender.get());
		}
		return tender.orElseThrow(RuntimeException::new);
	}
}
