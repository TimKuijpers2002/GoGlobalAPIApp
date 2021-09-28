package GoGlobalProject.APIApp.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="terrain")
public class Terrain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="terrain_id")
    private long terrainId;

    @Column(name="name")
    private String name;

    //USED FOR CREATION OF TABLE IN DB
    //@ManyToMany(mappedBy = "terrains", cascade = {CascadeType.ALL})
    //private List<Location> locations = new ArrayList<>();
}
