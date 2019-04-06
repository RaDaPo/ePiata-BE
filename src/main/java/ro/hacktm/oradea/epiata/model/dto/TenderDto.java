package ro.hacktm.oradea.epiata.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class TenderDto {
	private String name;
	private String description;
	private String unit;
	private String pricePerUnit;
	private String distance;
	private String owner;
	private boolean status;
	private Long userId;
	private List<UserDto> users;
	private List<Integer> acceptedUserIds;
}
