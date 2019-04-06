package ro.hacktm.oradea.epiata.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import ro.hacktm.oradea.epiata.model.dto.OfferDto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "OFFER")
@Data
public class OfferDao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private Long owner;
	private String quantity;
	private String price;
	private Long category;
	@Embedded
	private Location location;
	@Column(name = "start_date")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")

	private Date startDate;
	@Column(name = "end_date")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")

	private Date endDate;
	private String description;
	@ManyToOne()
	@JoinColumn(name = "user_id")
	private UserDao user;
	private Boolean active = true;

	public OfferDto toDto() {
		OfferDto dto = new OfferDto();
		dto.setDescription(this.getDescription());
		dto.setEndDate(this.getEndDate());
		dto.setName(this.getName());
		dto.setStartDate(this.getStartDate());
		dto.setOwner(this.getOwner());
		dto.setPrice(this.getPrice());
		dto.setQuantity(this.getQuantity());
		dto.setId(this.getId());
		dto.setActive(this.getActive());
		return dto;
	}

}
