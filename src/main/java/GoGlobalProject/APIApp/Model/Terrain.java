package GoGlobalProject.APIApp.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
}
