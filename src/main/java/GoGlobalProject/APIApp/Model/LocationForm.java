package GoGlobalProject.APIApp.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "location_form")
public class LocationForm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "location_form_id")
    private long locationFormId;

    @Embedded
    private Form baseForm;

    @Column(name = "coordinates")
    private String coordinates;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "location_form_facility",
            joinColumns = { @JoinColumn(name = "location_form_id") },
            inverseJoinColumns = { @JoinColumn(name = "facility_id") }
    )
    private List<Facility> facilities;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "location_form_category",
            joinColumns = { @JoinColumn(name = "location_form_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") }
    )
    private List<Category> categories;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "location_form_terrain",
            joinColumns = { @JoinColumn(name = "location_form_id") },
            inverseJoinColumns = { @JoinColumn(name = "terrain_id") }
    )
    private List<Terrain> terrains;
}
