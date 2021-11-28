package GoGlobalProject.APIApp.Controller;

import GoGlobalProject.APIApp.CustomError.ResourceNotFoundException;
import GoGlobalProject.APIApp.Interfaces.ILocationFormService;
import GoGlobalProject.APIApp.Interfaces.IService;
import GoGlobalProject.APIApp.Model.LocationForm;
import GoGlobalProject.APIApp.Repository.LocationFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class FormController {

    @Autowired
    private LocationFormRepository locationFormRepository;

    @Qualifier("locationFormService")
    @Autowired
    private ILocationFormService locationFormService;

    @GetMapping("/locationforms/{id}")
    public ResponseEntity<LocationForm> GetLocationFormById(@PathVariable(value = "id") long location_form_id) throws ResourceNotFoundException {
        LocationForm locationForm = locationFormRepository.findById(location_form_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Location form could not be found for id:" + location_form_id));
        return ResponseEntity.ok().body(locationForm);
    }

    @GetMapping("/locationforms")
    public ResponseEntity<?> GetAllLocationForm(){
        try {
            List<LocationForm> locationFormList = locationFormService.GetAll();
            return ResponseEntity.ok().body(locationFormList);
        } catch (
                NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/locationforms")
    public ResponseEntity<?> CreateLocationFrom(@RequestBody LocationForm locationForm){
        boolean hasError = locationFormService.Create(locationForm);
        if(hasError){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Location already exist for coordinates:" + locationForm.getLongitude() + locationForm.getLatitude());
        }
        return ResponseEntity.ok().body(locationForm);
    }

    @PostMapping("/locationformaccepted")
    public ResponseEntity<?> AcceptLocationForm(@RequestBody LocationForm locationForm){
        try{
            locationFormService.AcceptForm(locationForm);
            return ResponseEntity.ok().body(locationForm);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build();
        }
    }

    @DeleteMapping("/locationforms/{id}")
    public ResponseEntity<?> DeleteLocationFrom(@PathVariable(value = "id") long form_id) throws ResourceNotFoundException {
        locationFormRepository.findById(form_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Location form could not be found for id:" + form_id));
        locationFormService.Delete(form_id);
        return ResponseEntity.ok().build();
    }
}