package ro.hacktm.oradea.epiata.model.dto;

import lombok.Data;
import ro.hacktm.oradea.epiata.model.entity.Location;

@Data
public final class FilteredOffersRequest {
	public final String searchTerm;
	public final Long category;
	public final Location location;
}
