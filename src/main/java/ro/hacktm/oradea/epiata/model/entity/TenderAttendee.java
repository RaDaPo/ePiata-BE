package ro.hacktm.oradea.epiata.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class TenderAttendee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long userId;
	private Integer participationMass;
	private String name;
	private Boolean accepted = false;
	private Boolean rejected = false;
	@ManyToMany(mappedBy = "allTenderAttendees")
	@JsonIgnore
	private List<Tender> tenders;

}
