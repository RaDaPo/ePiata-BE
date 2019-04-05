package ro.hacktm.oradea.epiata.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ANNOUNCEMENT")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String owner;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "img_link")
    private String imgLocation;
    private String description;
}
