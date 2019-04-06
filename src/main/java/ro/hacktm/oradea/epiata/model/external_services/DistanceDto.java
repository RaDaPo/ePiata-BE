package ro.hacktm.oradea.epiata.model.external_services;

import lombok.Data;

@Data
public class DistanceDto {

	private long time;
	private int distance;

	private String timeInMin;
	private String distanceInKm;
}
