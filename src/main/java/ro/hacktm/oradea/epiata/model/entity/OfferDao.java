package ro.hacktm.oradea.epiata.model.entity;

import lombok.Data;
import ro.hacktm.oradea.epiata.model.dto.OfferDto;

import javax.persistence.*;

@Entity
@Table(name = "OFFER")
@Data
public class OfferDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long owner;
    private String quantity;
    private String price;
    private Long category;
    private String county;

    @Column(name = "start_date")
    private String startDate;
    @Column(name = "end_date")
    private String endDate;
    private String description;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserDao user;

    public OfferDto toDto() {
        OfferDto dto = new OfferDto();
        dto.setDescription(this.getDescription());
        dto.setEndDate(this.getEndDate());
        dto.setName(this.getName());
        dto.setStartDate(this.getStartDate());
        dto.setOwner(this.getOwner());
        dto.setPrice(this.getPrice());
        dto.setQuantity(this.getQuantity());
        dto.setId(this.getId());
        dto.setCounty(this.county);
        return dto;
    }

}
