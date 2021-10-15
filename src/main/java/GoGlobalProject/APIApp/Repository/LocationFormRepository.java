package GoGlobalProject.APIApp.Repository;

import GoGlobalProject.APIApp.Model.Form;
import GoGlobalProject.APIApp.Model.LocationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationFormRepository extends JpaRepository<LocationForm, Long> {

}
