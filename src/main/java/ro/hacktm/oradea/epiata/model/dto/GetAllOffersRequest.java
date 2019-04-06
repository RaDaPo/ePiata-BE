package ro.hacktm.oradea.epiata.model.dto;

import lombok.Data;

@Data
public class GetAllOffersRequest {

    public String searchTerm;
    public Long category;
    public String county;
}
