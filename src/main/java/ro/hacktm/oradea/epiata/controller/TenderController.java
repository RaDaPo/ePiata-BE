package ro.hacktm.oradea.epiata.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.hacktm.oradea.epiata.model.dto.TenderDto;
import ro.hacktm.oradea.epiata.model.entity.Tender;
import ro.hacktm.oradea.epiata.service.TenderService;

import java.util.List;

@RestController
@RequestMapping(path = "/tender")
public class TenderController {


    @Autowired
    private TenderService tenderService;

    @GetMapping(path = "/all")
    List<TenderDto> getAllUsers() {
        return tenderService.getAllTenders();
    }

    @PostMapping()
    Tender addTender(@RequestBody TenderDto tenderDto) {
        Tender tender = new Tender();
        BeanUtils.copyProperties(tenderDto, tender);
        tenderService.save(tender);
        return tender;

    }
}
