package GoGlobalProject.APIApp.Controller;

import GoGlobalProject.APIApp.Interfaces.IService;
import GoGlobalProject.APIApp.Model.Admin;
import GoGlobalProject.APIApp.Repository.AdminRepository;
import GoGlobalProject.APIApp.CustomError.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Qualifier("adminService")
    @Autowired
    private IService<Admin> adminService;

    @GetMapping("/admins")
    public List<Admin> GetAllAdmin(){
        return adminRepository.findAll();
    }

    @PostMapping("/admins")
    public Admin CreateAdmin(@RequestBody Admin admin){
        return adminService.Create(admin);
    }

    @GetMapping("/admins/{id}")
    public ResponseEntity<Admin> GetAdminById(@PathVariable(value = "id") long admin_id) throws ResourceNotFoundException {
        Admin admin = adminRepository.findById(admin_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Admin could not be found for id:" + admin_id));
        return ResponseEntity.ok().body(admin);
    }

    @PutMapping("/admins/{id}")
    public ResponseEntity<Admin> UpdateAdmin(@PathVariable(value = "id") long admin_id, @RequestBody Admin adminDetails) throws ResourceNotFoundException {
        Admin admin = adminRepository.findById(admin_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Admin could not be found for id:" + admin_id));
        admin.setName(adminDetails.getName());
        admin.setEmail(adminDetails.getPassword());
        admin.setPassword(adminDetails.getPassword());
        adminRepository.save(admin);
        return ResponseEntity.ok().body(admin);
    }

    @DeleteMapping("/admins/{id}")
    public ResponseEntity<?> DeleteAdmin(@PathVariable(value = "id") long admin_id) throws ResourceNotFoundException {
        adminRepository.findById(admin_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Admin could not be found for id:" + admin_id));
        adminRepository.deleteById(admin_id);
        return ResponseEntity.ok().build();
    }
}
