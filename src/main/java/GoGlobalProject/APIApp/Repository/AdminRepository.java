package GoGlobalProject.APIApp.Repository;

import GoGlobalProject.APIApp.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Modifying
    @Query(nativeQuery=true, value="DELETE a FROM admin a WHERE a.admin_id = :id")
    void deleteByAdminId(@Param("id")long id);

}
