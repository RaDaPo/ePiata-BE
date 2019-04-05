package ro.hacktm.oradea.epiata.model.entity;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
class Location {

	String address;
	int no;

}
