package ro.hacktm.oradea.epiata.model.dto;

import lombok.Data;
import ro.hacktm.oradea.epiata.model.entity.Fruits;
import ro.hacktm.oradea.epiata.model.entity.Vegetables;

@Data
public class CategoryDto {
	private Fruits fruits;
	private Vegetables vegetables;
}
