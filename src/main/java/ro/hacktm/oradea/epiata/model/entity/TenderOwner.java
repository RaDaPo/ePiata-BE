package ro.hacktm.oradea.epiata.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class TenderOwner {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Embedded
	private Location location;

	private String name;
	private String email;

	@OneToOne(mappedBy = "owner", cascade = CascadeType.ALL)
	@JsonBackReference
	private TenderDao tenderDao;

}
