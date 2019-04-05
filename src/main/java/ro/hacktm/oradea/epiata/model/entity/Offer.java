package ro.hacktm.oradea.epiata.model.entity;

import lombok.Data;
import ro.hacktm.oradea.epiata.model.dto.OfferDto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "OFFER")
@Data
public class Offer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String owner;
	@Embedded
	private Category category;
	@Column(name = "start_date")
	private Date startDate;
	@Column(name = "end_date")
	private Date endDate;
	@Column(name = "img_link")
	private String imgLocation;
	private String description;
	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;

	public OfferDto toDto() {
		OfferDto dto = new OfferDto();
		dto.setDescription(this.getDescription());
		dto.setEndDate(this.getEndDate());
		dto.setImgLocation(this.getImgLocation());
		dto.setName(this.getName());
		dto.setStartDate(this.getStartDate());
		dto.setOwner(this.getOwner());
		return dto;
	}

}
