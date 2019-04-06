package ro.hacktm.oradea.epiata.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import ro.hacktm.oradea.epiata.model.entity.CategoryType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class TenderAddRequest {

	private String tile;
	private String description;
	private String unit;
	private String pricePerUnit;
	private String distance;
	private Long ownerId;
	private boolean status;
	private Integer neededGrossMass;
	@Enumerated(EnumType.STRING)
	private CategoryType type;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date startDate;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date endDate;
}
