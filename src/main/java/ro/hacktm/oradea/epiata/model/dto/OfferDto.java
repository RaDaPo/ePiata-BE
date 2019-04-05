package ro.hacktm.oradea.epiata.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
public class OfferDto {

    private String name;
    private String owner;
    private Date startDate;
    private Date endDate;
    private String imgLocation;
    private String description;
}
