package GoGlobalProject.APIApp.Repository;

import GoGlobalProject.APIApp.Model.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {

}
