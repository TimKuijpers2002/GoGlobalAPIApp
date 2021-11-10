package GoGlobalProject.APIApp.Services;

import GoGlobalProject.APIApp.Interfaces.IService;
import GoGlobalProject.APIApp.Model.LocationForm;
import GoGlobalProject.APIApp.Repository.LocationFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationFormService implements IService<LocationForm> {

    @Autowired
    private LocationFormRepository locationFormRepository;

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
        locationFormRepository.save(locationForm);
        return false;
    }

    @Override
    public boolean Update(long notImplemented, LocationForm notImplemented2){
        return false;
    }

    @Override
    public void Delete(long form_id){
        if (locationFormRepository.existsById(form_id)) {
            locationFormRepository.deleteByLocationFormId(form_id);
        }
    }

    public boolean CheckForDoubleCoordinates(LocationForm locationForm){
        var getAllForms = locationFormRepository.findAll();

        for (var item :getAllForms) {
            if (locationForm.getCoordinates().equals(item.getCoordinates())) {
                    return true;
                }
        }
        return false;
    }
}
