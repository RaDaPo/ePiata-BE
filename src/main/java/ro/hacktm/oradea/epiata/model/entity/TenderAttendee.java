package ro.hacktm.oradea.epiata.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

}
