package GoGlobalProject.APIApp.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="facility")
public class Facility {

    @Id
    @Column(name="facility_id")
    private long facilityId;

    @Column(name="name")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "facilities")
    private Set<LocationForm> locationForms;

    @JsonIgnore
    @ManyToMany(mappedBy = "facilities")
    private Set<Location> locations;
}
