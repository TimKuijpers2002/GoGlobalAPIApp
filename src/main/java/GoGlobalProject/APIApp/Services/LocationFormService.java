package GoGlobalProject.APIApp.Services;

import GoGlobalProject.APIApp.Interfaces.ILocationFormService;
import GoGlobalProject.APIApp.Interfaces.IService;
import GoGlobalProject.APIApp.Model.Location;
import GoGlobalProject.APIApp.Model.LocationForm;
import GoGlobalProject.APIApp.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationFormService implements ILocationFormService {

    @Autowired
    private LocationFormRepository locationFormRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TerrainRepository terrainRepository;

    @Autowired
    private FacilityRepository facilityRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public LocationForm GetById(long form_id){
        return locationFormRepository.findById(form_id).get();
    }

    @Override
    public List<LocationForm> GetAll(){
        return locationFormRepository.findAll();
    }

    @Override
    public boolean Create(LocationForm locationForm){
        if(CheckForDoubleCoordinates(locationForm)){
            return CheckForDoubleCoordinates(locationForm);
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //Find category/facility/terrain and edit the location
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        locationFormRepository.save(locationForm);
        return false;
    }

    @Override
    public void Delete(long form_id){
        if (locationFormRepository.existsById(form_id)) {
            locationFormRepository.deleteByLocationFormId(form_id);
        }
    }

    @Override
    public Location AcceptForm(LocationForm locationForm){
        Location newLocation = new Location();
        newLocation.setTerrains(locationForm.getTerrains());
        newLocation.setName(locationForm.getName());
        newLocation.setLongitude(locationForm.getLongitude());
        newLocation.setLatitude((locationForm.getLatitude()));
        newLocation.setGeneralContent(locationForm.getBaseForm().getFormContent());
        newLocation.setFacilities(locationForm.getFacilities());
        newLocation.setCategories(locationForm.getCategories());
        locationFormRepository.deleteByLocationFormId(locationForm.getLocationFormId());
        return locationRepository.save(newLocation);
    }

    public boolean CheckForDoubleCoordinates(LocationForm locationForm){
        var getAllForms = locationFormRepository.findAll();

        for (var item :getAllForms) {
            if (locationForm.getLatitude().equals(item.getLatitude()) && locationForm.getLongitude().equals(item.getLongitude())) {
                    return true;
                }
        }
        return false;
    }
}
