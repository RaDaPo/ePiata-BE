package ro.hacktm.oradea.epiata.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
	private Integer neededGrossMass = 0;
	private Integer gatheredGrossMass = 0;
	private boolean status;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<String> users;
	private List<TenderAttendee> allTenderAttendees;
	private List<AcceptedUser> acceptedUserIds;

}
