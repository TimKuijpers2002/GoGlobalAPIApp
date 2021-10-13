package GoGlobalProject.APIApp.Controller;

import GoGlobalProject.APIApp.CustomError.ResourceNotFoundException;
import GoGlobalProject.APIApp.Interfaces.IService;
import GoGlobalProject.APIApp.Model.Category;
import GoGlobalProject.APIApp.Model.Terrain;
import GoGlobalProject.APIApp.Repository.TerrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class TerrainController {

    @Autowired
    private TerrainRepository terrainRepository;

    @Qualifier("terrainService")
    @Autowired
    private IService<Terrain> terrainService;

    @GetMapping("/terrains/{id}")
    public ResponseEntity<?> GetTerrainById(@PathVariable(value = "id") long terrain_id) throws ResourceNotFoundException {
        Terrain terrain = terrainRepository.findById(terrain_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Terrain could not be found for id:" + terrain_id));
        return ResponseEntity.ok().body(terrain);
    }

    @GetMapping("/terrains")
    public ResponseEntity<?> GetAllTerrainTypes(){
        try {
            List<Terrain> terrainList = terrainService.GetAll();
            return ResponseEntity.ok().body(terrainList);
        } catch (
                NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/terrains")
    public ResponseEntity<?> CreateTerrain(@RequestBody Terrain terrain){
        boolean hasError = terrainService.Create(terrain);
        if(hasError){
            return ResponseEntity.badRequest().body("Terrain already exist for name:" + terrain.getName());
        }
        return ResponseEntity.ok().body(terrain);
    }

    @PutMapping("/terrains/{id}")
    public ResponseEntity<?> UpdateTerrain(@PathVariable(value = "id") long terrain_id, @RequestBody Terrain terrainDetails) throws Exception {
        Terrain terrain = terrainRepository.findById(terrain_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Terrain could not be found for id:" + terrain_id));
        boolean hasError = terrainService.Update(terrain_id, terrainDetails);
        if(hasError){
            return ResponseEntity.badRequest().body("Terrain already exist for name:" + terrainDetails.getName());
        }
        return ResponseEntity.ok().body(terrain);
    }

    @DeleteMapping("/terrains/{id}")
    public ResponseEntity<?> DeleteTerrain(@PathVariable(value = "id") long terrain_id) throws ResourceNotFoundException {
        terrainRepository.findById(terrain_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Terrain could not be found for id:" + terrain_id));
        terrainService.Delete(terrain_id);
        return ResponseEntity.ok().build();
    }
}
