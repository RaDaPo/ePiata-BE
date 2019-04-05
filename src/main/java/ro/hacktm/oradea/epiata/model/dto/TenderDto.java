package ro.hacktm.oradea.epiata.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TenderDto {

    private String name;
    private String description;
    private String unit;
    private String pricePerUnit;
    private String distance;
    private String owner;
    private boolean status;

}
