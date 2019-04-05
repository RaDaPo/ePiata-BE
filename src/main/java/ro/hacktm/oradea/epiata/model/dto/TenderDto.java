package ro.hacktm.oradea.epiata.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ro.hacktm.oradea.epiata.model.entity.User;

import java.util.List;

@Data
public class TenderDto {


    private String name;
    private String description;
    private String unit;
    private String pricePerUnit;
    private String distance;
    private String owner;
    private boolean status;
    private Long userId;
    private List<User> users;


}
