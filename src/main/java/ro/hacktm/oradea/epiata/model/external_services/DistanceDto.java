package ro.hacktm.oradea.epiata.model.external_services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class DistanceDto {

    @JsonIgnore
    private long time;
    @JsonIgnore
    private long distance;

    private String timeToDestination;
    private String distanceBetweenLocations;
}
