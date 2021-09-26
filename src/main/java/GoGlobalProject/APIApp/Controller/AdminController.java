package GoGlobalProject.APIApp.Controller;

import GoGlobalProject.APIApp.Model.Admin;
import GoGlobalProject.APIApp.Repository.AdminRepository;
import GoGlobalProject.APIApp.Services.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/admin")
    public List<Admin> GetAllAdmin(){
        return adminRepository.findAll();
    }

    @PostMapping("/admin")
    public Admin CreateAdmin(@RequestBody Admin admin){
        return adminRepository.save(admin);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<Admin> UpdateAdmin(@PathVariable(value = "id") long admin_id) throws ResourceNotFoundException {
        Admin admin = adminRepository.findById(admin_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Admin could not be found for id:" + admin_id));
        return ResponseEntity.ok().body(admin);
    }
}
