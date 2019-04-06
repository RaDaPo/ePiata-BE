package ro.hacktm.oradea.epiata.model.entity;

import lombok.Data;
import ro.hacktm.oradea.epiata.model.dto.OfferDto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "OFFER")
@Data
public class OfferDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String owner;

    @JoinColumn
    private Long category;

    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "img_link")
    private String imgLocation;
    private String description;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserDao user;

    public OfferDto toDto() {
        OfferDto dto = new OfferDto();
        dto.setDescription(this.getDescription());
        dto.setEndDate(this.getEndDate().toString());
        dto.setImgLocation(this.getImgLocation());
        dto.setName(this.getName());
        dto.setStartDate(this.getStartDate().toString());
        dto.setOwner(this.getOwner());
        return dto;
    }

}
