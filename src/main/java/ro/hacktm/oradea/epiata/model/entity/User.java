package ro.hacktm.oradea.epiata.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import ro.hacktm.oradea.epiata.model.dto.UserDto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USER")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String address;
    private String email;
    @OneToMany(mappedBy="user")
    @JsonBackReference
    private List<Tender> tenders;
    @OneToMany(mappedBy="user")
    @JsonBackReference
    private List<Offer> offers;


    public UserDto toDto() {
        UserDto dto = new UserDto();
        dto.setAddress(this.getAddress());
        dto.setEmail(this.getEmail());
        dto.setName(this.getName());
        return dto;
    }
}
