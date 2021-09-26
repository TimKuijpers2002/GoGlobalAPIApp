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

    //@Column(name="Facilities")
    //private List<Facility> facilities;

    //@Column(name="Terrains")
    //private List<Terrain> terrains;

    //@Column(name="Categories")
    //private List<Category> categories;

    @Column(name="likes")
    private long likes;

    //@Column(name="comments")
    //private List<String> comments;
}
