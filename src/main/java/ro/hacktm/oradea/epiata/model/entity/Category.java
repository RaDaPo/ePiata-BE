package ro.hacktm.oradea.epiata.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import ro.hacktm.oradea.epiata.model.dto.CategoryDto;

import javax.persistence.*;

@Entity(name = "CATEGORIES")
@Data
public class Category {

	@Id
	@GeneratedValue
	private Long id;
	@Enumerated(EnumType.STRING)
	private CategoryType type;
	private String name;

	@OneToOne(mappedBy = "owner", cascade = CascadeType.ALL)
	@JsonBackReference
	private TenderDao tenderDao;

	public CategoryDto toDto() {
		CategoryDto dto = new CategoryDto();
		dto.setId(getId());
		dto.setType(getType());
		dto.setName(getName());
		return dto;
	}
}
