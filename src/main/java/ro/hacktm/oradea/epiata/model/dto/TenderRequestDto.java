package ro.hacktm.oradea.epiata.model.dto;

import lombok.Data;

import java.util.List;

@Data
class TenderRequestDto {
	private String title;
	private String description;
	private String unit;
	private Double pricePerUnit;
	private Long ownerId;
	private Integer neededGrossMass;
	private Long userId;
	private List<UserDto> users;
	private List<Integer> acceptedUserIds;
}
