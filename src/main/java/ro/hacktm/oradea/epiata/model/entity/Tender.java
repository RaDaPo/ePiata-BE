package ro.hacktm.oradea.epiata.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import ro.hacktm.oradea.epiata.model.dto.TenderDto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TENDER")
@Data
public class Tender {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	private String unit;
	@Column(name = "price_unit")
	private String pricePerUnit;
	private String distance;
	private String owner;
	private boolean status;
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(
			name = "User_Tender",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "tender_id")}
	)
	@JsonBackReference
	private List<User> users;

	public TenderDto toDto() {
		TenderDto dto = new TenderDto();
		dto.setDescription(this.getDescription());
		dto.setDistance(this.getDistance());
		dto.setName(this.getName());
		dto.setOwner(this.getOwner());
		dto.setPricePerUnit(this.getPricePerUnit());
		dto.setUnit(this.getUnit());
		dto.setUsers(this.getUsers());
		return dto;
	}
}
