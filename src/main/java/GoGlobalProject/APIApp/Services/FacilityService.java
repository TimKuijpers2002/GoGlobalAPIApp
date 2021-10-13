package GoGlobalProject.APIApp.Services;

import GoGlobalProject.APIApp.Interfaces.IService;
import GoGlobalProject.APIApp.Model.Admin;
import GoGlobalProject.APIApp.Model.Facility;
import GoGlobalProject.APIApp.Repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityService implements IService<Facility> {

    @Autowired
    private FacilityRepository facilityRepository;

    public Facility GetById(long facility_id){
        return facilityRepository.findById(facility_id).get();
    }

    @Override
    public List<Facility> GetAll() {
        return facilityRepository.findAll();
    }

    @Override
    public boolean Create(Facility facility) {
        if(CheckForDoubleName(facility)){
            return CheckForDoubleName(facility);
        }
        facilityRepository.save(facility);
        return false;
    }

    @Override
    public boolean Update(long facility_id, Facility facilityDetails) {
        Facility facilityOriginal = GetById(facility_id);
        if(CheckForDoubleName(facilityDetails)){
            return CheckForDoubleName(facilityDetails);
        }
        facilityOriginal.setName(facilityDetails.getName());
        facilityRepository.save(facilityOriginal);
        return false;
    }

    @Override
    public void Delete(long facility_id) {
        facilityRepository.deleteById(facility_id);
    }

    public boolean CheckForDoubleName(Facility facility){
        var getAllCategoryTypes = facilityRepository.findAll();

        for (var item :getAllCategoryTypes) {
            if (facility.getName().equals(item.getName())) {
                return true;
            }
        }
        return false;
    }
}
