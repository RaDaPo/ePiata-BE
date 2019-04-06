package ro.hacktm.oradea.epiata.model.dto;

import lombok.Data;
import ro.hacktm.oradea.epiata.model.entity.CategoryType;

@Data
public class CategoryDto {
	private Long id;
	private CategoryType type;
	private String name;
}
