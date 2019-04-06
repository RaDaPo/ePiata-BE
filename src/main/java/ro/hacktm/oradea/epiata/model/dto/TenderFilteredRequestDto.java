package ro.hacktm.oradea.epiata.model.dto;

import lombok.Data;
import ro.hacktm.oradea.epiata.model.entity.CategoryType;

@Data
public class TenderFilteredRequestDto {

	private CategoryType categoryType;
	private String county;
}
