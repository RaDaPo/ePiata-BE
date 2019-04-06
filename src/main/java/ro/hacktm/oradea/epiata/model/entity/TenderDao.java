package ro.hacktm.oradea.epiata.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import ro.hacktm.oradea.epiata.model.dto.TenderResponseDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TENDER")
@Data
public class TenderDao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	@Column(name = "price_unit")
	private Double pricePerUnit;
	private Integer neededGrossMass = 0;
	private Integer gatheredGrossMass = 0;
	private String description;
	private Boolean active = true;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date startDate;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date endDate;
	@Enumerated(EnumType.STRING)
	private CategoryType type;
	private String unit;
	@OneToMany(cascade = {CascadeType.ALL})
	@JsonManagedReference
	@JsonIgnore
	private List<AcceptedUser> acceptedUserIds;
	@OneToOne(cascade = {CascadeType.ALL})
	private TenderOwner owner;
	@OneToMany(cascade = {CascadeType.ALL})
	@JsonManagedReference
	@JsonIgnore
	private List<TenderAttendee> allTenderAttendees;
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(
			name = "User_Tender",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "tender_id")}
	)
	private List<UserDao> acceptedUsers = new ArrayList<>();
	private Location location;

	public TenderResponseDto toDto() {
		TenderResponseDto dto = new TenderResponseDto();
		dto.setDescription(this.getDescription());
		dto.setTitle(this.getTitle());
		dto.setPricePerUnit(this.getPricePerUnit());
		dto.setUnit(this.getUnit());
		dto.setOwnerName(this.getOwner().getName());
		dto.setAllTenderAttendees(this.getAllTenderAttendees());
		dto.setAcceptedUserIds(this.getAcceptedUserIds());
		dto.setGatheredGrossMass(this.getGatheredGrossMass());
		dto.setNeededGrossMass(this.getNeededGrossMass());
		dto.setActive(this.getActive());
		dto.setStartDate(this.getStartDate());
		dto.setEndDate(this.getEndDate());
		dto.setLocation(this.getLocation());
		dto.setType(this.getType());
		return dto;
	}
}
