package ro.hacktm.oradea.epiata.model.dto;

import lombok.Data;

@Data
public class OfferDto {
	private String name;
	private Long owner;
	private String startDate;
	private String endDate;
	private String description;
	private Long id;
	private String quantity;
	private String price;
}
