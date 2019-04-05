package ro.hacktm.oradea.epiata.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Fruits fruits;

    @Enumerated(EnumType.STRING)
    private Vegetables vegetables;
}
