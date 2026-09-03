package com.KambaFlix.Controller;

import com.KambaFlix.Controller.request.CategoryRequest;
import com.KambaFlix.Controller.response.CategoryResponse;
import com.KambaFlix.Entity.Category;
import com.KambaFlix.Service.CategoryService;
import com.KambaFlix.mapper.CategoryMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kambaflix/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<CategoryResponse> getAllCategory(){
        List<Category> categoryList = categoryService.findAll();
        return categoryList.stream()
                .map(CategoryMapper::toCategoryResponce)
                .toList();
    }

    @PostMapping("/cadastrar")
    public CategoryResponse postCadastroDeCategory(@RequestBody CategoryRequest request){
        Category category = CategoryMapper.toCategory(request);
        Category categorysave = categoryService.cadastroDeCategory(category);
        return CategoryMapper.toCategoryResponce(categorysave);
    }

    @GetMapping("/{id}")
    public CategoryResponse getByCategoryId(@PathVariable Long id){
        Category category = categoryService.findById(id);
        return CategoryMapper.toCategoryResponce(category);
    }

    @DeleteMapping("/{id}")
    public  void deleteByCategoryId(@PathVariable Long id){
         categoryService.delete(id);
    }

}
