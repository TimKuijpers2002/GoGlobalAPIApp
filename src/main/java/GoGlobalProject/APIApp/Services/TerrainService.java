package GoGlobalProject.APIApp.Services;

import GoGlobalProject.APIApp.Interfaces.IService;
import GoGlobalProject.APIApp.Model.Admin;
import GoGlobalProject.APIApp.Model.Terrain;
import GoGlobalProject.APIApp.Repository.TerrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerrainService implements IService<Terrain> {

    @Autowired
    private TerrainRepository terrainRepository;

    public Terrain GetById(long terrain_id){
        return terrainRepository.findById(terrain_id).get();
    }

    @Override
    public List<Terrain> GetAll() {
        return terrainRepository.findAll();
    }

    @Override
    public boolean Create(Terrain terrain) {
        if(CheckForDoubleName(terrain)){
            return CheckForDoubleName(terrain);
        }
        terrainRepository.save(terrain);
        return false;
    }

    @Override
    public boolean Update(long terrain_id, Terrain terrainDetails) {
        Terrain terrainOriginal = GetById(terrain_id);
        if(CheckForDoubleName(terrainDetails)){
            return CheckForDoubleName(terrainDetails);
        }
        terrainOriginal.setName(terrainDetails.getName());
        terrainRepository.save(terrainOriginal);
        return false;
    }

    @Override
    public void Delete(long terrain_id) {
        terrainRepository.deleteById(terrain_id);
    }

    public boolean CheckForDoubleName(Terrain terrain){
        var getAllTerrainTypes = terrainRepository.findAll();

        for (var item :getAllTerrainTypes) {
            if (terrain.getName().equals(item.getName())) {
                return true;
            }
        }
        return false;
    }
}
