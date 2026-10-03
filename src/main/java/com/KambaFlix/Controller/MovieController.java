package com.KambaFlix.Controller;


import com.KambaFlix.Controller.request.MovieRequest;
import com.KambaFlix.Controller.response.MovieResponse;
import com.KambaFlix.Entity.Movie;
import com.KambaFlix.Service.MovieService;
import com.KambaFlix.mapper.MovieMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kambaflix/movie")
@RequiredArgsConstructor
@Tag(name = "Filmes", description = "Consulta, criação, atualização e remoção de filmes.")
@SecurityRequirement(name = "bearerAuth")
public class MovieController {

    private  final MovieService movieService;

    @GetMapping()
    @Operation(summary = "Listar filmes", description = "Devolve todos os filmes disponíveis no catálogo.")
    @ApiResponse(responseCode = "200", description = "Lista de filmes devolvida com sucesso.")
    public ResponseEntity<List<MovieResponse>> findAll(){
        List<MovieResponse> movies = movieService.findAll()
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
        return ResponseEntity.ok(movies);
    }

    @PostMapping("/cadastrar")
    @Operation(summary = "Criar filme", description = "Adiciona um filme ao catálogo.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Filme criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos; o título é obrigatório.")
    })
    public ResponseEntity<MovieResponse>  SavedMovie(@Valid @RequestBody MovieRequest request){
        Movie movie = movieService.SavedMovie(MovieMapper.toMovie(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(MovieMapper.toMovieResponse(movie));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter filme", description = "Procura um filme pelo seu identificador.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Filme encontrado."),
            @ApiResponse(responseCode = "404", description = "Não existe um filme com o identificador indicado.")
    })
    public ResponseEntity<?> findById(@PathVariable Long id){
        if (movieService.findById(id) != null){
            Movie movieS = movieService.findById(id);
            return ResponseEntity.ok(movieS);
        }else{
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("A Streaming com IDs: "+id+" nao encontrado nos nossos banco de dados");
        }

    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar filme", description = "Atualiza os dados de um filme existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Filme atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos; o título é obrigatório."),
            @ApiResponse(responseCode = "404", description = "Não existe um filme com o identificador indicado.")
    })
    public ResponseEntity<MovieResponse> alterByMovie(@PathVariable Long id,@Valid @RequestBody MovieRequest request){
        return movieService.alterByMovie(id,MovieMapper.toMovie(request))
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie))).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    @Operation(summary = "Pesquisar filmes por categoria", description = "Devolve os filmes associados à categoria indicada.")
    @ApiResponse(responseCode = "200", description = "Resultados da pesquisa devolvidos com sucesso.")
    public ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam Long categoryId){
            return ResponseEntity.ok(movieService.findByCategory(categoryId)
                    .stream()
                    .map(MovieMapper::toMovieResponse)
                    .toList());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover filme", description = "Remove um filme do catálogo.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Filme removido com sucesso."),
            @ApiResponse(responseCode = "404", description = "Não existe um filme com o identificador indicado.")
    })
    public  ResponseEntity<String> deleteByCategoryId(@PathVariable Long id){
        if (movieService.findById(id) != null) {
            movieService.deleteByCategoryId(id);
            return ResponseEntity.ok("Categoria com IDs: "+id+" excluido com sucesso");
        }else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("A categoria com IDs: "+id+" nao encontrado nos nossos banco de dados");
        }
    }
}
