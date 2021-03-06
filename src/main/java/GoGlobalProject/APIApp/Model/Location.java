package GoGlobalProject.APIApp.Model;

import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="location_id")
    private long locationId;

    @Column(name="longitude")
    private Float longitude;

    @Column(name="latitude")
    private Float latitude;

    @Column(name="name")
    private String name;

    @Column(name="general_content")
    private String generalContent;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "location_facility",
            joinColumns = { @JoinColumn(name = "location_id", referencedColumnName="location_id") },
            inverseJoinColumns = { @JoinColumn(name = "facility_id", referencedColumnName="facility_id") }
    )
    private Set<Facility> facilities;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "location_terrain",
            joinColumns = { @JoinColumn(name = "location_id", referencedColumnName="location_id") },
            inverseJoinColumns = { @JoinColumn(name = "terrain_id", referencedColumnName="terrain_id") }
    )
    private Set<Terrain> terrains;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "location_category",
            joinColumns = { @JoinColumn(name = "location_id", referencedColumnName="location_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id", referencedColumnName="category_id") }
    )
    private Set<Category> categories;

    @Column(name="likes")
    private long likes;
}
