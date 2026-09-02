package com.KambaFlix.Controller;

import com.KambaFlix.Entity.Category;
import com.KambaFlix.Service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kambaflix/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }


    @GetMapping("/lista")
    public List<Category> getAllCategory(){
        return categoryService.findAll();
    }

    @PostMapping("/cadastrar")
    public Category postCadastroDeCategory(@RequestBody Category category){
        return categoryService.cadastroDeCategory(category);
    }

    @GetMapping("/lista/{id}")
    public Category getAllCategoryById(@PathVariable Long id){
        return categoryService.findById(id);
    }


    @DeleteMapping("/lista/{id}")
    public  void delete(@PathVariable Long id){
         categoryService.delete(id);
    }

}
