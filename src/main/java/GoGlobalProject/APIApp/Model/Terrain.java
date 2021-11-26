package GoGlobalProject.APIApp.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="terrain")  
public class Terrain {

    @Id
    @Column(name="terrain_id")
    private long terrainId;

    @Column(name="name")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "terrains")
    private Set<LocationForm> locationForms;

    @JsonIgnore
    @ManyToMany(mappedBy = "terrains")
    private Set<Location> locations;

}
