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

    @GetMapping("/admins/{id}")
    public ResponseEntity<?> GetAdminById(@PathVariable(value = "id") long admin_id) throws ResourceNotFoundException {
        Admin admin = adminRepository.findById(admin_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Admin could not be found for id:" + admin_id));
        return ResponseEntity.ok().body(admin);
    }

    @GetMapping("/admins")
    public List<Admin> GetAllAdmin(){
        return adminRepository.findAll();
    }

    @PostMapping("/admins")
    public ResponseEntity<?> CreateAdmin(@RequestBody Admin admin){
        boolean hasError = adminService.Create(admin);
        if(hasError){
            return ResponseEntity.badRequest().body("Admin already exist for email:" + admin.getEmail());
        }
        return ResponseEntity.ok().body(admin);
    }

    @PutMapping("/admins/{id}")
    public ResponseEntity<?> UpdateAdmin(@PathVariable(value = "id") long admin_id, @RequestBody Admin adminDetails) throws Exception {
        Admin admin = adminRepository.findById(admin_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Admin could not be found for id:" + admin_id));
        boolean hasError = adminService.Update(admin, adminDetails, admin_id);
        if(hasError){
            return ResponseEntity.badRequest().body("Admin already exist for email:" + adminDetails.getEmail());
        }
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
