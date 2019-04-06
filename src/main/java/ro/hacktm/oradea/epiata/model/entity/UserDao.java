package ro.hacktm.oradea.epiata.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import ro.hacktm.oradea.epiata.model.dto.UserDto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USER")
@Data
public class UserDao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Embedded
	private Location location;

	private String name;
	private String email;
	@ManyToMany(mappedBy = "users")
	@JsonIgnore
	private List<TenderDao> tenders;
	@OneToMany(mappedBy = "user")
	@JsonBackReference
	private List<OfferDao> offers;

	public UserDto toDto() {
		UserDto dto = new UserDto();
		dto.setEmail(this.getEmail());
		dto.setName(this.getName());
		dto.setLocation(this.location);
		return dto;
	}
}
