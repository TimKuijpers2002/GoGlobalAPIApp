package GoGlobalProject.APIApp.UnitTests;

import GoGlobalProject.APIApp.Controller.AdminController;
import GoGlobalProject.APIApp.CustomError.ResourceNotFoundException;
import GoGlobalProject.APIApp.Model.Admin;
import GoGlobalProject.APIApp.Repository.AdminRepository;
import GoGlobalProject.APIApp.Services.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminControllerTest {

    @InjectMocks
    AdminController controller;

    @Mock
    AdminRepository adminRepository;

    @Mock
    AdminService adminService;

    @Test
    public void ReturnListWithAdmins_WhenRequested(){
        //Arrange
        List<Admin> list = new ArrayList<>();
        list.add(new Admin(0, "Tim", "TimKuijpers2002@outlook.com", "TestPWTim"));
        list.add(new Admin(1, "Bob", "BobHertzen2005@outlook.com", "TestPWBob"));

        when(adminService.GetAll()).thenReturn(list); //

        //Act
        ResponseEntity<List<Admin>> result = controller.GetAllAdmin();

        //Assert
        assertThat(result.getStatusCodeValue())
                .isEqualTo(200);
    }

    @Test
    public void ReturnAdminById_WhenRequested() throws ResourceNotFoundException {
        //Arrange
        Admin admin = new Admin(0L, "Tim", "TimKuijpers2002@outlook.com", "TestPWTim");

        when(adminRepository.findById(0L)).thenReturn(java.util.Optional.of(admin)); //

        //Act
        ResponseEntity<Admin> result = controller.GetAdminById(0L);

        //Assert
        assertThat(result.getStatusCodeValue())
                .isEqualTo(200);
    }

    @Test
    public void ShouldNOTPOSTAdmin_WhenDoubleEmail_isGiven(){
        //Arrange
        List<Admin> list = new ArrayList<>();
        Admin empOne = new Admin(1L, "John", "test1@email.nl", "password1");
        Admin empTwo = new Admin(2L, "Alex", "test2@email.nl","password2");
        Admin empThree = new Admin(3L, "Steve", "test3@email.nl","password3");

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        when(adminService.GetAll()).thenReturn(list); // Mock

        //Act
        Admin admin = new Admin(0L, "John","test1@email.nl","password");
        when(adminService.Create(any(Admin.class))).thenReturn(true);
        when(adminService.CheckForDoubleEmails(any(Admin.class))).thenReturn(true);

        ResponseEntity<Admin> response = controller.CreateAdmin(admin);

        //Assert
        assertThat(response.getStatusCodeValue())
                .isEqualTo(409);
    }

    @Test
    public void ShouldDELETEAdmin_WhenIDisGiven() throws ResourceNotFoundException {
        //Arrange
        Admin adminToDelete = new Admin(1L, null, null,null);

        //Act
        when(adminRepository.findById(1L)).thenReturn(java.util.Optional.of(adminToDelete)); // Mock
        ResponseEntity<Admin> response = controller.DeleteAdmin(adminToDelete.getAdminId());

        //Assert
        assertThat(response.getStatusCodeValue())
                .isEqualTo(200);
    }

    @Test
    public void ShouldPOSTAdmin_WhenDataGiven(){
        //Arrange
        Admin admin = new Admin(1L, "John", "test1@email.nl", "password1");
        when(adminService.Create(any(Admin.class))).thenReturn(false);
        when(adminService.CheckForDoubleEmails(any(Admin.class))).thenReturn(false);

        //Act
        ResponseEntity<Admin> response = controller.CreateAdmin(admin);

        //Assert
        assertThat(response.getBody())
                .isEqualTo(admin);
    }
}
