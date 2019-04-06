package ro.hacktm.oradea.epiata.model.external_services;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GeoCodeResponseContent {

    @JsonProperty(value = "View")
    private List<GeoCodeView> geoCodeView;
}
