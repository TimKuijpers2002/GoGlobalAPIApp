package GoGlobalProject.APIApp.Interfaces;

import GoGlobalProject.APIApp.Model.Location;
import GoGlobalProject.APIApp.Model.LocationForm;

import java.util.List;

public interface ILocationFormService {

    Location AcceptForm(LocationForm locationForm);
    LocationForm GetById(long form_id);
    boolean Create(LocationForm locationForm);
    void Delete(long form_id);
    List<LocationForm> GetAll();

}
