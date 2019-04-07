package ro.hacktm.oradea.epiata.model.entity;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Location {

	String city;
	String county;
	String street;
	Integer streetNo;
	String latitude;
	String longitude;
	String zip;
	String timeToDestination;
	String distance;

}
