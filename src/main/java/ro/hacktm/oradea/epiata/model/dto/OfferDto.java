package ro.hacktm.oradea.epiata.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class OfferDto {
	private String name;
	private Long owner;
	private String description;
	private Long id;
	private String quantity;
	private String price;
	private Boolean active;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date startDate;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date endDate;

}
