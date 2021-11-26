package GoGlobalProject.APIApp.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

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

    @Column(name = "longitude")
    private Float longitude;

    @Column(name = "latitude")
    private Float latitude;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "location_form_facility",
            joinColumns = { @JoinColumn(name = "location_form_id", referencedColumnName="location_form_id") },
            inverseJoinColumns = { @JoinColumn(name = "facility_id", referencedColumnName="facility_id") }
    )
    private Set<Facility> facilities;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "location_form_category",
            joinColumns = { @JoinColumn(name = "location_form_id", referencedColumnName="location_form_id")},
            inverseJoinColumns = { @JoinColumn(name = "category_id", referencedColumnName="category_id")}
    )
    private Set<Category> categories;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "location_form_terrain",
            joinColumns = { @JoinColumn(name = "location_form_id", referencedColumnName="location_form_id") },
            inverseJoinColumns = { @JoinColumn(name = "terrain_id", referencedColumnName="terrain_id") }
    )
    private Set<Terrain> terrains;
}
