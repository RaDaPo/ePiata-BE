package ro.hacktm.oradea.epiata.model.dto;

import lombok.Data;
import ro.hacktm.oradea.epiata.model.entity.Location;

import javax.persistence.Embedded;
import java.util.List;

@Data
public class UserDto {

    private String name;
    private String address;
    private String email;
    private List<TenderDto> tenders;
    private List<OfferDto> offers;
    private Long id;

    @Embedded
    private Location location;
}
