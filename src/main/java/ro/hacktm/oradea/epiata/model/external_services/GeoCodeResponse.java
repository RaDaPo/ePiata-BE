package ro.hacktm.oradea.epiata.model.external_services;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GeoCodeResponse {

	@JsonProperty(value = "Response")
	private GeoCodeResponseContent geoCodeResponseContent;
}
