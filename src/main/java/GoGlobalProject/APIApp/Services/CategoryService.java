package GoGlobalProject.APIApp.Services;

import GoGlobalProject.APIApp.Interfaces.IService;
import GoGlobalProject.APIApp.Model.Category;
import GoGlobalProject.APIApp.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements IService<Category> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> GetAll() {
        return categoryRepository.findAll();
    }

    @Override
    public boolean Create(Category category) {
        if(CheckForDoubleName(category)){
            return CheckForDoubleName(category);
        }
        categoryRepository.save(category);
        return false;
    }

    @Override
    public boolean Update(Category categoryOriginal, Category categoryDetails) {
        if(CheckForDoubleName(categoryDetails)){
            return CheckForDoubleName(categoryDetails);
        }
        categoryOriginal.setName(categoryDetails.getName());
        categoryRepository.save(categoryOriginal);
        return false;
    }

    @Override
    public void Delete(long category_id) {
        categoryRepository.deleteById(category_id);
    }

    public boolean CheckForDoubleName(Category category){
        var getAllCategoryTypes = categoryRepository.findAll();

        for (var item :getAllCategoryTypes) {
            if (category.getName().equals(item.getName())) {
                return true;
            }
        }
        return false;
    }
}
