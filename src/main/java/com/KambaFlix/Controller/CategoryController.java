package com.KambaFlix.Controller;

import com.KambaFlix.Controller.request.CategoryRequest;
import com.KambaFlix.Controller.response.CategoryResponse;
import com.KambaFlix.Entity.Category;
import com.KambaFlix.Service.CategoryService;
import com.KambaFlix.mapper.CategoryMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kambaflix/category")
@Tag(name = "Categorias", description = "Gestão das categorias de filmes.")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping()
    @Operation(summary = "Listar categorias", description = "Devolve todas as categorias disponíveis.")
    @ApiResponse(responseCode = "200", description = "Lista de categorias devolvida com sucesso.")
    public ResponseEntity<List<CategoryResponse>> getAllCategory(){
        List<CategoryResponse> categoryList = categoryService.findAll()
                .stream()
                .map(CategoryMapper::toCategoryResponce)
                .toList();
        return ResponseEntity.ok(categoryList);
    }

    @PostMapping("/cadastrar")
    @Operation(summary = "Criar categoria", description = "Adiciona uma nova categoria.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos; o nome é obrigatório.")
    })
    public ResponseEntity<CategoryResponse> postCadastroDeCategory(@Valid @RequestBody CategoryRequest request){
        Category category = CategoryMapper.toCategory(request);
        Category categorysave = categoryService.cadastroDeCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toCategoryResponce(categorysave));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter categoria", description = "Procura uma categoria pelo seu identificador.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoria encontrada."),
            @ApiResponse(responseCode = "404", description = "Não existe uma categoria com o identificador indicado.")
    })
    public ResponseEntity<?> getByCategoryId(@PathVariable Long id){
        if (categoryService.findById(id) != null) {
            Category category = categoryService.findById(id);
            return ResponseEntity.ok(CategoryMapper.toCategoryResponce(category));
        }else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("A categoria com IDs: "+id+" nao encontrado nos nossos banco de dados");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover categoria", description = "Remove uma categoria.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoria removida com sucesso."),
            @ApiResponse(responseCode = "404", description = "Não existe uma categoria com o identificador indicado.")
    })
    public  ResponseEntity<String> deleteByCategoryId(@PathVariable Long id){
        if (categoryService.findById(id) != null) {
            categoryService.delete(id);
            return ResponseEntity.ok("Categoria com IDs: "+id+" excluido com sucesso");
        }else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("A categoria com IDs: "+id+" nao encontrado nos nossos banco de dados");
        }
    }

}
