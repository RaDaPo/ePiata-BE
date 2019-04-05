package ro.hacktm.oradea.epiata.model.entity;

import ro.hacktm.oradea.epiata.model.dto.CategoryDto;

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

    public CategoryDto toDto() {
        CategoryDto dto = new CategoryDto();
        dto.setFruits(this.fruits);
        dto.setVegetables(this.vegetables);
        return dto;
    }
}
