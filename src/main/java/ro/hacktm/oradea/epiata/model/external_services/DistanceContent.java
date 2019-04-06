package ro.hacktm.oradea.epiata.model.external_services;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DistanceContent {

    @JsonProperty(value = "results")
    List<DistanceDto> distanceContentList;
}
