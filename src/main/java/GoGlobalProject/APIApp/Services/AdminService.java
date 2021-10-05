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
    public List<Admin> GetAll() {
        return adminRepository.findAll();
    }

    @Override
    public boolean Create(Admin admin) {
        if(CheckForDoubleEmails(admin)){
            return CheckForDoubleEmails(admin);
        }
        adminRepository.save(admin);
        return false;
    }

    @Override
    public boolean Update(Admin adminOriginal, Admin adminDetails) {
        if(CheckForDoubleEmails(adminDetails)){
            return CheckForDoubleEmails(adminDetails);
        }
        adminOriginal.setName(adminDetails.getName());
        adminOriginal.setEmail(adminDetails.getEmail());
        adminOriginal.setPassword(adminDetails.getPassword());
        adminRepository.save(adminOriginal);
        return false;
    }

    @Override
    public void Delete(long admin_id) {
        adminRepository.deleteById(admin_id);
    }

    public boolean CheckForDoubleEmails(Admin admin){
        var getAllUsers = adminRepository.findAll();

        for (var item :getAllUsers) {
            if (admin.getEmail().equals(item.getEmail())) {
                return true;
            }
        }
        return false;
    }
}
