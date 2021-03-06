package ro.hacktm.oradea.epiata.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ro.hacktm.oradea.epiata.model.entity.AcceptedUser;
import ro.hacktm.oradea.epiata.model.entity.Location;
import ro.hacktm.oradea.epiata.model.entity.TenderAttendee;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Data
public class TenderResponseDto {

	private Long id;
	private String title;
	private String description;
	private String unit;
	private Double pricePerUnit;
	private String distance;
	private String ownerName;
	private Integer neededGrossMass = 0;
	private Integer gatheredAcceptedGrossMass = 0;
	private Integer offeredGrossMass = 0;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<String> users;
	private List<TenderAttendee> allTenderAttendees;
	private List<AcceptedUser> acceptedUserIds;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date startDate;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date endDate;
	private Boolean active;
	private Location location;
	private String categoryName;
	private String categoryType;
}
