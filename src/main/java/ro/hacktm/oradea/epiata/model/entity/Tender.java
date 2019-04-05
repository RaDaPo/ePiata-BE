package ro.hacktm.oradea.epiata.model.entity;

import lombok.Data;
import ro.hacktm.oradea.epiata.model.dto.TenderDto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TENDER")
@Data
public class Tender {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String unit;
    @Column(name = "price_unit")
    private String pricePerUnit;
    private String distance;
    private String owner;
    private boolean status;
    @ManyToMany(mappedBy = "tenders")
    private List<User> user;

    public TenderDto toDto() {
        TenderDto dto = new TenderDto();
        dto.setDescription(this.getDescription());
        dto.setDistance(this.getDistance());
        dto.setName(this.getName());
        dto.setOwner(this.getOwner());
        dto.setPricePerUnit(this.getPricePerUnit());
        dto.setUnit(this.getUnit());
        return dto;
    }
}
