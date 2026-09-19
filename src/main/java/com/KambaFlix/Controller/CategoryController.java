package com.KambaFlix.Controller;

import com.KambaFlix.Controller.request.CategoryRequest;
import com.KambaFlix.Controller.response.CategoryResponse;
import com.KambaFlix.Entity.Category;
import com.KambaFlix.Service.CategoryService;
import com.KambaFlix.mapper.CategoryMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<CategoryResponse>> getAllCategory(){
        List<CategoryResponse> categoryList = categoryService.findAll()
                .stream()
                .map(CategoryMapper::toCategoryResponce)
                .toList();
        return ResponseEntity.ok(categoryList);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<CategoryResponse> postCadastroDeCategory(@RequestBody CategoryRequest request){
        Category category = CategoryMapper.toCategory(request);
        Category categorysave = categoryService.cadastroDeCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toCategoryResponce(categorysave));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getByCategoryId(@PathVariable Long id){
        if (categoryService.findById(id) != null) {
            Category category = categoryService.findById(id);
            return ResponseEntity.ok(CategoryMapper.toCategoryResponce(category));
        }else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("A categoria com IDs: "+id+" nao encontrado nos nossos banco de dados");
        }
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteByCategoryId(@PathVariable Long id){
        if (categoryService.findById(id) != null) {
            categoryService.delete(id);
            return ResponseEntity.ok("Categoria com IDs: "+id+" excluido com sucesso");
        }else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("A categoria com IDs: "+id+" nao encontrado nos nossos banco de dados");
        }
    }

}
