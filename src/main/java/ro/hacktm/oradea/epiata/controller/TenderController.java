package ro.hacktm.oradea.epiata.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.hacktm.oradea.epiata.model.dto.TenderDto;
import ro.hacktm.oradea.epiata.model.entity.TenderDao;
import ro.hacktm.oradea.epiata.model.entity.UserDao;
import ro.hacktm.oradea.epiata.service.TenderService;
import ro.hacktm.oradea.epiata.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@SuppressWarnings("unused")
@RequestMapping(path = "/api/tenders")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TenderController {

	private final TenderService tenderService;
	private final UserService userService;

	@GetMapping
	List<TenderDto> getAllUsers() {
		return tenderService.getAllTenders();
	}

	@PostMapping
	TenderDao addTender(@RequestBody TenderDto tenderDto) {
		TenderDao tender = new TenderDao();
		BeanUtils.copyProperties(tenderDto, tender);
		tenderService.save(tender);
		return tender;
	}

	@PutMapping(value = "/{id}")
	TenderDao updateTender(@RequestBody TenderDto tenderDto, @RequestParam(name = "id") Long id) {
		Optional<TenderDao> tender = tenderService.getTenderById(id);
		if (tender.isPresent()) {
			Optional<UserDao> user = userService.getUserById(id);
			user.ifPresent(value -> tender.get().getUsers().add(value));
			tenderService.save(tender.get());
		}
		return tender.orElseThrow(RuntimeException::new);
	}
}
