package ro.hacktm.oradea.epiata.model.entity;

import lombok.Data;
import ro.hacktm.oradea.epiata.model.dto.CategoryDto;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Data
public class Category {

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
