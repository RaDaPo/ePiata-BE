package ro.hacktm.oradea.epiata.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TenderDto {

    private String name;
    private String description;
    private String unit;
    private String pricePerUnit;
    private String distance;
    private String owner;
    private boolean status;

}
