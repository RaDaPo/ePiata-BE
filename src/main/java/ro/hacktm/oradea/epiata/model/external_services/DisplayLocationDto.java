package ro.hacktm.oradea.epiata.model.external_services;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DisplayLocationDto {

    @JsonProperty(value = "Latitude")
    private String latitude;

    @JsonProperty(value = "Longitude")
    private String longitude;
}
