package ro.hacktm.oradea.epiata.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ro.hacktm.oradea.epiata.model.dto.UserDto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USER")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Embedded
	private Location location;

	private String name;
	private String email;
	@ManyToMany(mappedBy = "acceptedUsers")
	@JsonIgnore
	private List<Tender> tenders;
	@OneToMany(mappedBy = "user")
	@JsonBackReference
	private List<Offer> offers;
	private Boolean accepted;

	public UserDto toDto() {
		UserDto dto = new UserDto();
		dto.setEmail(this.getEmail());
		dto.setName(this.getName());
		dto.setAccepted(this.accepted);
		dto.setLocation(this.location);
		return dto;
	}

	public TenderOwner toOwnerDao() {
		TenderOwner dto = new TenderOwner();
		dto.setEmail(this.getEmail());
		dto.setName(this.getName());
		dto.setLocation(this.location);
		return dto;
	}
}
