package GoGlobalProject.APIApp.Controller;

import GoGlobalProject.APIApp.Model.Location;
import GoGlobalProject.APIApp.Repository.LocationRepository;
import GoGlobalProject.APIApp.CustomError.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/locations")
    public List<Location> GetAllLocation(){
        return locationRepository.findAll();
    }

    @PostMapping("/locations")
    public Location CreateLocation(@RequestBody Location location){
        return locationRepository.save(location);
    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<Location> GetLocationById(@PathVariable(value = "id") long location_id) throws ResourceNotFoundException {
        Location location = locationRepository.findById(location_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Location could not be found for id:" + location_id));
        return ResponseEntity.ok().body(location);
    }

    @PutMapping("/locations/{id}")
    public ResponseEntity<Location> UpdateLocation(@PathVariable(value = "id") long location_id, @RequestBody Location locationDetails) throws ResourceNotFoundException {
        Location location = locationRepository.findById(location_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Location could not be found for id:" + location_id));
        location.setCoordinates(locationDetails.getCoordinates());
        location.setName(locationDetails.getName());
        location.setGeneralContent(locationDetails.getGeneralContent());
        location.setFacilities(locationDetails.getFacilities());
        location.setTerrains(locationDetails.getTerrains());
        location.setCategories(locationDetails.getCategories());
        location.setLikes(locationDetails.getLikes());
        location.setComments(locationDetails.getComments());

        locationRepository.save(location);
        return ResponseEntity.ok().body(location);
    }

    @DeleteMapping("/locations/{id}")
    public ResponseEntity<?> DeleteLocation(@PathVariable(value = "id") long location_id) throws ResourceNotFoundException {
        locationRepository.findById(location_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Location could not be found for id:" + location_id));
        locationRepository.deleteById(location_id);
        return ResponseEntity.ok().build();
    }
}
