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
@Table(name="category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="category_id")
    private long categoryId;

    @Column(name="name")
    private String name;
}
