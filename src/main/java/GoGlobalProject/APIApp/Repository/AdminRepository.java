package GoGlobalProject.APIApp.Repository;

import GoGlobalProject.APIApp.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Modifying
    @Query(nativeQuery=true, value="DELETE FROM admin WHERE admin_id = ?1")
    void deleteByAdminId(long id);

}
