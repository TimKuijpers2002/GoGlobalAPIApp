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
@Table(name="category")
public class Category {

    @Id
    @Column(name="category_id")
    private long categoryId;

    @Column(name="name")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private Set<LocationForm> locationForms;

    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private Set<Location> locations;
}
