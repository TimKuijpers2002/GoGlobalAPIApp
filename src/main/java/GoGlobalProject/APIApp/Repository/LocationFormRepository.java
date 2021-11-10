package GoGlobalProject.APIApp.Repository;

import GoGlobalProject.APIApp.Model.LocationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface LocationFormRepository extends JpaRepository<LocationForm, Long> {

    @Modifying
    @Query(nativeQuery=true, value="DELETE FROM location_form WHERE location_form_id = ?1")
    void deleteByLocationFormId(long id);
}
