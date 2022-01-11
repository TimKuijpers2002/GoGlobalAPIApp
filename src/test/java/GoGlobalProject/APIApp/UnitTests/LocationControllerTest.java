package GoGlobalProject.APIApp.UnitTests;

import GoGlobalProject.APIApp.Controller.LocationController;
import GoGlobalProject.APIApp.CustomError.ResourceNotFoundException;
import GoGlobalProject.APIApp.Model.Location;
import GoGlobalProject.APIApp.Repository.LocationRepository;
import GoGlobalProject.APIApp.Services.LocationService;
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
public class LocationControllerTest {

    @InjectMocks
    LocationController controller;

    @Mock
    LocationRepository locationRepository;

    @Mock
    LocationService locationService;

    @Test
    public void ReturnListWithLocations_WhenRequested(){
        //Arrange
        List<Location> list = new ArrayList<>();
        list.add(new Location(0L, 10F, 20F, "loc1", "content1", null, null, null, 0));
        list.add(new Location(1L, 15F, 25F, "loc2", "content2", null, null, null, 2));

        when(locationService.GetAll()).thenReturn(list); //

        //Act
        ResponseEntity<List<Location>> result = controller.GetAllLocation();

        //Assert
        assertThat(result.getStatusCodeValue())
                .isEqualTo(200);
    }

    @Test
    public void ReturnLocationById_WhenRequested() throws ResourceNotFoundException {
        //Arrange
        Location location = new Location(0L, 10F, 20F, "loc1", "content1", null, null, null, 0);

        when(locationRepository.findById(0L)).thenReturn(java.util.Optional.of(location)); //

        //Act
        ResponseEntity<Location> result = controller.GetLocationById(0L);

        //Assert
        assertThat(result.getStatusCodeValue())
                .isEqualTo(200);
    }

    @Test
    public void ShouldNOTPOSTLocation_WhenDoubleCoordinate_isGiven(){
        //Arrange
        List<Location> list = new ArrayList<>();
        Location empOne = new Location(0L, 10F, 20F, "loc1", "content1", null, null, null, 0);
        Location empTwo = new Location(1L, 15F, 25F, "loc2", "content2", null, null, null, 1);
        Location empThree = new Location(2L, 20F, 30F, "loc3", "content3", null, null, null, 2);

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        when(locationService.GetAll()).thenReturn(list); // Mock

        //Act
        Location location = new Location(0L, 10F, 20F, "loc1", "content1", null, null, null, 0);
        when(locationService.Create(any(Location.class))).thenReturn(true);
        when(locationService.CheckForDoubleCoordinates(any(Location.class))).thenReturn(true);

        ResponseEntity<Location> response = controller.CreateLocation(location);

        //Assert
        assertThat(response.getStatusCodeValue())
                .isEqualTo(409);
    }

    @Test
    public void ShouldDELETELocation_WhenIDisGiven() throws ResourceNotFoundException {
        //Arrange
        Location locationToDelete = new Location(1L, 10F, 20F, "loc1", "content1", null, null, null, 0);

        //Act
        when(locationRepository.findById(1L)).thenReturn(java.util.Optional.of(locationToDelete)); // Mock
        ResponseEntity<Location> response = controller.DeleteLocation(locationToDelete.getLocationId());

        //Assert
        assertThat(response.getStatusCodeValue())
                .isEqualTo(200);
    }

    @Test
    public void ShouldPOSTLocation_WhenDataGiven(){
        //Arrange
        Location location = new Location(0L, 10F, 20F, "loc1", "content1", null, null, null, 0);
        when(locationService.Create(any(Location.class))).thenReturn(false);
        when(locationService.CheckForDoubleCoordinates(any(Location.class))).thenReturn(false);

        //Act
        ResponseEntity<Location> response = controller.CreateLocation(location);

        //Assert
        assertThat(response.getBody())
                .isEqualTo(location);
    }
}
