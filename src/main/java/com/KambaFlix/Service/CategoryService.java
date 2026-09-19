package com.KambaFlix.Service;

import com.KambaFlix.Entity.Category;
import com.KambaFlix.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }


    public List<Category> findAll(){
        return repository.findAll();
    }

    public  Category cadastroDeCategory(Category category){
        return repository.save(category);
    }

    public Category findById(Long id){
        Optional<Category> category = repository.findById(id);
        return category.orElse(null);
    }

    public  void delete(Long id){
         repository.deleteById(id);
    }


}
