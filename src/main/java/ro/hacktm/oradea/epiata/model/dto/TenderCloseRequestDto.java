package ro.hacktm.oradea.epiata.model.dto;

import lombok.Data;

@Data
public class TenderCloseRequestDto {

	private Long tenderId;
	private Integer newNeededGrossMass;
}
