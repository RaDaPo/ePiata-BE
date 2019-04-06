package ro.hacktm.oradea.epiata.model.dto;

import lombok.Data;
import ro.hacktm.oradea.epiata.model.entity.Location;

@Data
public class GetAllOffersRequest {

    public final String searchTerm;
    public final String category;
    public final Location location;
}
