package ro.hacktm.oradea.epiata.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import ro.hacktm.oradea.epiata.model.dto.TenderResponseDto;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "TENDER")
@Data
public class TenderDao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@OneToMany(cascade = {CascadeType.ALL})
	@JsonManagedReference
	@JsonIgnore
	private List<AcceptedUser> acceptedUserIds;
	private String description;
	private String unit;
	@OneToOne(cascade = {CascadeType.ALL})
	private TenderOwner owner;
	@Column(name = "price_unit")
	private String pricePerUnit;
	private String distance;
	private Integer neededGrossMass = 0;
	private Integer gatheredGrossMass = 0;
	private boolean status;
	@OneToMany(cascade = {CascadeType.ALL})
	@JsonManagedReference
	@JsonIgnore
	private List<TenderAttendee> tenderAttendees;

	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(
			name = "User_Tender",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "tender_id")}
	)
	private List<UserDao> users;

	public TenderResponseDto toDto() {
		TenderResponseDto dto = new TenderResponseDto();
		dto.setDescription(this.getDescription());
		dto.setDistance(this.getDistance());
		dto.setName(this.getName());
		dto.setPricePerUnit(this.getPricePerUnit());
		dto.setUnit(this.getUnit());
		dto.setOwnerName(this.getOwner().getName());
		dto.setUsers(this.getUsers().stream().map(UserDao::getName).collect(Collectors.toList()));
		dto.setTenderAttendees(this.getTenderAttendees());
		dto.setAcceptedUserIds(this.getAcceptedUserIds());
		dto.setGatheredGrossMass(this.getGatheredGrossMass());
		dto.setNeededGrossMass(this.getNeededGrossMass());
		return dto;
	}
}
