package GoGlobalProject.APIApp.Controller;

import GoGlobalProject.APIApp.Interfaces.ILocationService;
import GoGlobalProject.APIApp.Interfaces.IService;
import GoGlobalProject.APIApp.Model.Admin;
import GoGlobalProject.APIApp.Model.Category;
import GoGlobalProject.APIApp.Model.Location;
import GoGlobalProject.APIApp.Repository.LocationRepository;
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
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @Qualifier("locationService")
    @Autowired
    private ILocationService<Location> locationService;

    @GetMapping("/locations/{id}")
    public ResponseEntity<Location> GetLocationById(@PathVariable(value = "id") long location_id) throws ResourceNotFoundException {
        Location location = locationRepository.findById(location_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Location could not be found for id:" + location_id));
        return ResponseEntity.ok().body(location);
    }

    @GetMapping("/locations")
    public ResponseEntity<?> GetAllLocation(){
        try {
            List<Location> locationList = locationService.GetAll();
            return ResponseEntity.ok().body(locationList);
        } catch (
                NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/locations")
    public ResponseEntity<?> CreateLocation(@RequestBody Location location){
        boolean hasError = locationService.Create(location);
        if(hasError){
            return ResponseEntity.badRequest().body("Location already exist for coordinates:" + location.getLongitude() + location.getLatitude());
        }
        return ResponseEntity.ok().body(location);
    }

    @PutMapping("/locations/{id}")
    public ResponseEntity<?> UpdateLocation(@PathVariable(value = "id") long location_id, @RequestBody Location locationDetails) throws ResourceNotFoundException {
        Location location = locationRepository.findById(location_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Location could not be found for id:" + location_id));
        boolean hasError = locationService.Update(location_id, locationDetails);
        if(hasError){
            return ResponseEntity.badRequest().body("Location already exist for coordinates:" + location.getLongitude() + location.getLatitude());
        }
        return ResponseEntity.ok().body(location);
    }

    @DeleteMapping("/locations/{id}")
    public ResponseEntity<?> DeleteLocation(@PathVariable(value = "id") long location_id) throws ResourceNotFoundException {
        locationRepository.findById(location_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Location could not be found for id:" + location_id));
        locationService.Delete(location_id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/locations/like/{id}")
    public ResponseEntity<?> LikeLocation(@PathVariable(value = "id") long location_id) throws ResourceNotFoundException {
        locationRepository.findById(location_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Location could not be found for id:" + location_id));
        locationService.LikeLocation(location_id);
        return ResponseEntity.ok().build();
    }
}