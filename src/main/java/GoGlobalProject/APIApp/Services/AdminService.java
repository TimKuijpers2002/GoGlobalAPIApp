package GoGlobalProject.APIApp.Services;

import GoGlobalProject.APIApp.Interfaces.IService;
import GoGlobalProject.APIApp.Model.Admin;
import GoGlobalProject.APIApp.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService implements IService<Admin> {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin GetById(long admin_id) {
        return adminRepository.findById(admin_id).get();
    }

    @Override
    public List<Admin> GetAll() {
        return adminRepository.findAll();
    }

    @Override
    public Admin Create(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public void Update(Admin adminOriginal, Admin adminDetails, long admin_id) {
        adminOriginal.setName(adminDetails.getName());
        adminOriginal.setEmail(adminDetails.getEmail());
        adminOriginal.setPassword(adminDetails.getPassword());

        adminRepository.save(adminOriginal);
    }

    @Override
    public void Delete(long admin_id) {
        adminRepository.deleteById(admin_id);
    }
}
