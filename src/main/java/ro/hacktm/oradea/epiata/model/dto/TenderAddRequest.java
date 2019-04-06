package ro.hacktm.oradea.epiata.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class TenderAddRequest {

	private String name;
	private String description;
	private String unit;
	private String pricePerUnit;
	private String distance;
	private Long ownerId;
	private boolean status;
	private Integer neededGrossMass;
}
