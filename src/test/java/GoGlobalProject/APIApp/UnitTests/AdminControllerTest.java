package GoGlobalProject.APIApp.UnitTests;

import GoGlobalProject.APIApp.Controller.AdminController;
import GoGlobalProject.APIApp.CustomError.ResourceNotFoundException;
import GoGlobalProject.APIApp.Model.Admin;
import GoGlobalProject.APIApp.Repository.AdminRepository;
import GoGlobalProject.APIApp.Services.AdminService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
    void ReturnListWithAdmin_WhenRequested(){
        //Arrange
        List<Admin> list = new ArrayList<>();
        list.add(new Admin(0, "Tim", "TimKuijpers2002@outlook.com", "TestPWTim"));
        list.add(new Admin(1, "Bob", "BobHertzen2005@outlook.com", "TestPWBob"));

        when(adminService.GetAll()).thenReturn(list); //

        //Act
        ResponseEntity<?> result = controller.GetAllAdmin();

        //Assert
        assertThat(result.getStatusCode().toString())
                .isEqualTo("200 OK");
    }

    @Test
    void ReturnAdminById_WhenRequested() throws ResourceNotFoundException {
        //Arrange
        Admin admin = new Admin(0L, "Tim", "TimKuijpers2002@outlook.com", "TestPWTim");

        when(adminRepository.findById(0L)).thenReturn(java.util.Optional.of(admin)); //

        //Act
        ResponseEntity<?> result = controller.GetAdminById(0L);

        //Assert
        assertThat(result.getStatusCode().toString())
                .isEqualTo("200 OK");
    }
}
