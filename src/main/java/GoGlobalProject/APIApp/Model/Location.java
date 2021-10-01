package GoGlobalProject.APIApp.Model;

import lombok.*;
import javax.persistence.*;
import java.util.List;

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

    @Column(name="coordinates")
    private String coordinates;

    @Column(name="name")
    private String name;

    @Column(name="general_content")
    private String generalContent;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "location_facility",
            joinColumns = { @JoinColumn(name = "location_id") },
            inverseJoinColumns = { @JoinColumn(name = "facility_id") }
    )
    private List<Facility> facilities;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "location_terrain",
            joinColumns = { @JoinColumn(name = "location_id") },
            inverseJoinColumns = { @JoinColumn(name = "terrain_id") }
    )
    private List<Terrain> terrains;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "location_category",
            joinColumns = { @JoinColumn(name = "location_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") }
    )
    private List<Category> categories;

    @Column(name="likes")
    private long likes;
}
