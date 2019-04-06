package ro.hacktm.oradea.epiata.model.entity;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Location {

	String address;
	Integer streetNo;
	String latitude;
	String longitude;

}
