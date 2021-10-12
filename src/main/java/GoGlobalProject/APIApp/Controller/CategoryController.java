package GoGlobalProject.APIApp.Controller;

import GoGlobalProject.APIApp.CustomError.ResourceNotFoundException;
import GoGlobalProject.APIApp.Interfaces.IService;
import GoGlobalProject.APIApp.Model.Admin;
import GoGlobalProject.APIApp.Model.Category;
import GoGlobalProject.APIApp.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Qualifier("categoryService")
    @Autowired
    private IService<Category> categoryService;

    @GetMapping("/categories/{id}")
    public ResponseEntity<?> GetCategoryById(@PathVariable(value = "id") long category_id) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(category_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Category could not be found for id:" + category_id));
        return ResponseEntity.ok().body(category);
    }

    @GetMapping("/categories")
    public ResponseEntity<?> GetAllCategoryTypes(){
        try {
            List<Category> categoryList = categoryService.GetAll();
            return ResponseEntity.ok().body(categoryList);
        } catch (
                NoSuchElementException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/categories")
    public ResponseEntity<?> CreateCategory(@RequestBody Category category){
        boolean hasError = categoryService.Create(category);
        if(hasError){
            return ResponseEntity.badRequest().body("Category already exist for name:" + category.getName());
        }
        return ResponseEntity.ok().body(category);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<?> UpdateCategory(@PathVariable(value = "id") long category_id, @RequestBody Category categoryDetails) throws Exception {
        Category category = categoryRepository.findById(category_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Category could not be found for id:" + category_id));
        boolean hasError = categoryService.Update(category, categoryDetails);
        if(hasError){
            return ResponseEntity.badRequest().body("Category already exist for name:" + categoryDetails.getName());
        }
        return ResponseEntity.ok().body(category);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> DeleteCategory(@PathVariable(value = "id") long category_id) throws ResourceNotFoundException {
        categoryRepository.findById(category_id)
                .orElseThrow(() -> new ResourceNotFoundException("ERROR 404 \n Category could not be found for id:" + category_id));
        categoryRepository.deleteById(category_id);
        return ResponseEntity.ok().build();
    }
}
