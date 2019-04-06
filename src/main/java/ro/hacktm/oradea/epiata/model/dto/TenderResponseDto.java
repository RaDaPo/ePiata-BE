package ro.hacktm.oradea.epiata.model.dto;

import lombok.Data;
import ro.hacktm.oradea.epiata.model.entity.AcceptedUser;
import ro.hacktm.oradea.epiata.model.entity.TenderAttendee;

import java.util.List;

@Data
public class TenderResponseDto {

	private String name;
	private String description;
	private String unit;
	private String pricePerUnit;
	private String distance;
	private String ownerName;
	private boolean status;
	private List<String> users;
	private List<TenderAttendee> tenderAttendees;
	private List<AcceptedUser> acceptedUserIds;

}
