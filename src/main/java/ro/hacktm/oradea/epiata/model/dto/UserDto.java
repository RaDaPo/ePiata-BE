package ro.hacktm.oradea.epiata.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ro.hacktm.oradea.epiata.model.entity.Location;

import javax.persistence.Embedded;
import java.util.List;

@Data
public class UserDto {

	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private String name;
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private String address;
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private String email;
	@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
	private List<TenderDto> tenders;
	@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
	private List<OfferDto> offers;
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private Long id;
	@JsonInclude(value = JsonInclude.Include.NON_NULL)
	private Boolean accepted;

	@Embedded
	private Location location;
}
