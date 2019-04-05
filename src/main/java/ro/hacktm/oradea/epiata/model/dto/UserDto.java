package ro.hacktm.oradea.epiata.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserDto {

    private String name;
    private String address;
    private String email;
}
