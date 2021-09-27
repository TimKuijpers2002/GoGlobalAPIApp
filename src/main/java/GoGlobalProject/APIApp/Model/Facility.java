package GoGlobalProject.APIApp.Model;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="facility")
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="facility_id")
    private long facilityId;

    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name="location_id")
    private Location location;
}
