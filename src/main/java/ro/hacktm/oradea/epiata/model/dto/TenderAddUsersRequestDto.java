package ro.hacktm.oradea.epiata.model.dto;

import lombok.Data;

@Data
public class TenderAddUsersRequestDto {

	private Long userId;
	private Long tenderId;
	private Integer participationMass;

}
