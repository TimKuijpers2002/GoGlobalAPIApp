package GoGlobalProject.APIApp.Services;

import GoGlobalProject.APIApp.Interfaces.ILocationService;
import GoGlobalProject.APIApp.Model.Location;
import GoGlobalProject.APIApp.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService implements ILocationService<Location> {

    @Autowired
    private LocationRepository locationRepository;

    public Location GetById(long locationId){
        return locationRepository.findById(locationId).get();
    }

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
    public boolean Update(long locationId, Location locationDetails) {
        Location locationOriginal = GetById(locationId);
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

    @Override
    public void LikeLocation(long locationId) {
        Location location = GetById(locationId);
        long oldLikes = location.getLikes();
        oldLikes++;
        location.setLikes(oldLikes);
        locationRepository.save(location);
    }
}
