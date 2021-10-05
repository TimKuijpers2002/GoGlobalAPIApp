package GoGlobalProject.APIApp.Services;

import GoGlobalProject.APIApp.Interfaces.IService;
import GoGlobalProject.APIApp.Model.Location;
import GoGlobalProject.APIApp.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService implements IService<Location> {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Location> GetAll() {
        return locationRepository.findAll();
    }

    @Override
    public boolean Create(Location location) {
        if(CheckForDoubleCoordinates(location)){
            return CheckForDoubleCoordinates(location);
        }
        locationRepository.save(location);
        return false;
    }

    @Override
    public boolean Update(Location locationOriginal, Location locationDetails) {
        if(CheckForDoubleCoordinates(locationDetails)){
            return CheckForDoubleCoordinates(locationDetails);
        }
        locationOriginal.setCoordinates(locationDetails.getCoordinates());
        locationOriginal.setName(locationDetails.getName());
        locationOriginal.setGeneralContent(locationDetails.getGeneralContent());
        locationOriginal.setFacilities(locationDetails.getFacilities());
        locationOriginal.setTerrains(locationDetails.getTerrains());
        locationOriginal.setCategories(locationDetails.getCategories());
        locationOriginal.setLikes(locationDetails.getLikes());

        locationRepository.save(locationOriginal);
        return false;
    }

    @Override
    public void Delete(long location_id) {
        locationRepository.deleteById(location_id);
    }

    public boolean CheckForDoubleCoordinates(Location location){
        var getAllLocations = locationRepository.findAll();

        for (var item :getAllLocations) {
            if (location.getCoordinates().equals(item.getCoordinates())) {
                return true;
            }
        }
        return false;
    }
}
