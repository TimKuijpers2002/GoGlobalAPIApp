package GoGlobalProject.APIApp.Controller;

import GoGlobalProject.APIApp.CustomError.ResourceNotFoundException;
import GoGlobalProject.APIApp.Interfaces.IService;
import GoGlobalProject.APIApp.Model.Facility;
import GoGlobalProject.APIApp.Repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FacilityController {

    @Autowired
    private FacilityRepository facilityRepository;

    @Qualifier("facilityService")
    @Autowired
    private IService<Facility> facilityService;

    @GetMapping("/facilities/{id}")
    public ResponseEntity<?> GetFacilityById(@PathVariable(value = "id") long facility_id) throws ResourceNotFoundException {
        Facility category = facilityRepository.findById(facility_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Facility could not be found for id:" + facility_id));
        return ResponseEntity.ok().body(category);
    }

    @GetMapping("/facilities")
    public List<Facility> GetAllFacilityTypes(){
        return facilityRepository.findAll();
    }

    @PostMapping("/facilities")
    public ResponseEntity<?> CreateFacility(@RequestBody Facility facility){
        boolean hasError = facilityService.Create(facility);
        if(hasError){
            return ResponseEntity.badRequest().body("Facility already exist for name:" + facility.getName());
        }
        return ResponseEntity.ok().body(facility);
    }

    @PutMapping("/facilities/{id}")
    public ResponseEntity<?> UpdateFacility(@PathVariable(value = "id") long facility_id, @RequestBody Facility facilityDetails) throws Exception {
        Facility facility = facilityRepository.findById(facility_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Facility could not be found for id:" + facility_id));
        boolean hasError = facilityService.Update(facility, facilityDetails);
        if(hasError){
            return ResponseEntity.badRequest().body("Facility already exist for name:" + facilityDetails.getName());
        }
        return ResponseEntity.ok().body(facility);
    }

    @DeleteMapping("/facilities/{id}")
    public ResponseEntity<?> DeleteFacility(@PathVariable(value = "id") long facility_id) throws ResourceNotFoundException {
        facilityRepository.findById(facility_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Facility could not be found for id:" + facility_id));
        facilityRepository.deleteById(facility_id);
        return ResponseEntity.ok().build();
    }
}
