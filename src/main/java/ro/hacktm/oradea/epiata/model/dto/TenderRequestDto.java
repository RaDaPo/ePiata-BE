package ro.hacktm.oradea.epiata.model.dto;

import lombok.Data;

import java.util.List;

@Data
class TenderRequestDto {
	private String title;
	private String description;
	private String unit;
	private String pricePerUnit;
	private String distance;
	private Long ownerId;
	private boolean status;
	private Integer neededGrossMass;
	private Long userId;
	private List<UserDto> users;
	private List<Integer> acceptedUserIds;
}
