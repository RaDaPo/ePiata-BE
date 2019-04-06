package ro.hacktm.oradea.epiata.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import ro.hacktm.oradea.epiata.model.entity.Location;

import javax.persistence.Embedded;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class OfferDto {

	@Embedded
	Location location;
	private Long id;
	private String name;
	private Long owner;
	private String quantity;
	private String price;
	private String description;
	private Boolean active;
	private Long category;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date startDate;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date endDate;

}
