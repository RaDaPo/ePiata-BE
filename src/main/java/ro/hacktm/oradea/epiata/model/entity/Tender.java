package ro.hacktm.oradea.epiata.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "TENDER")
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
}
