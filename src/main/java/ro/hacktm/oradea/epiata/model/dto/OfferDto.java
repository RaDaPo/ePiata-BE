package ro.hacktm.oradea.epiata.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OfferDto {

    private String name;
    private String owner;
    private Date startDate;
    private Date endDate;
    private String imgLocation;
    private String description;
}
