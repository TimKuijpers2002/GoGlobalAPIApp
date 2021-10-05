package GoGlobalProject.APIApp.Controller;

import GoGlobalProject.APIApp.Interfaces.IService;
import GoGlobalProject.APIApp.Model.Admin;
import GoGlobalProject.APIApp.Repository.AdminRepository;
import GoGlobalProject.APIApp.CustomError.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
    public ResponseEntity<?> GetAllAdmin(){
        try {
            List<Admin> adminList = adminService.GetAll();
            return ResponseEntity.ok().body(adminList);
        } catch (
                NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
        boolean hasError = adminService.Update(admin, adminDetails);
        if(hasError){
            return ResponseEntity.badRequest().body("Admin already exist for email:" + adminDetails.getEmail());
        }
        return ResponseEntity.ok().body(admin);
    }

    @DeleteMapping("/admins/{id}")
    public ResponseEntity<?> DeleteAdmin(@PathVariable(value = "id") long admin_id) throws ResourceNotFoundException {
        adminRepository.findById(admin_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Admin could not be found for id:" + admin_id));
        adminService.Delete(admin_id);
        return ResponseEntity.ok().build();
    }
}
