package ro.hacktm.oradea.epiata.model.entity;

import lombok.Data;
import ro.hacktm.oradea.epiata.model.dto.CategoryDto;

import javax.persistence.*;

@Entity(name = "CATEGORIES")
@Data
public class CategoryDao {

	@Id
	@GeneratedValue
	private Long id;
	@Enumerated(EnumType.STRING)
	private CategoryType type;
	private String name;

	public CategoryDto toDto() {
		CategoryDto dto = new CategoryDto();
		dto.setId(getId());
		dto.setType(getType());
		dto.setName(getName());
		return dto;
	}
}
