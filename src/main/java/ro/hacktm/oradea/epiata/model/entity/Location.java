package ro.hacktm.oradea.epiata.model.entity;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Location {

    String address;
    int no;
    String latitude;
    String longitude;

}
