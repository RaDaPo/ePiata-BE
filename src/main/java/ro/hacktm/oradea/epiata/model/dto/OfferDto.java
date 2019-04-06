package ro.hacktm.oradea.epiata.model.dto;

import lombok.Data;

@Data
public class OfferDto {
	private String name;
	private String owner;
	private String startDate;
	private String endDate;
	private String imgLocation;
	private String description;
}
