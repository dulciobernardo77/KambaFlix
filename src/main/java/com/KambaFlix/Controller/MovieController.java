package com.KambaFlix.Controller;


import com.KambaFlix.Controller.request.MovieRequest;
import com.KambaFlix.Controller.response.MovieResponse;
import com.KambaFlix.Entity.Movie;
import com.KambaFlix.Service.MovieService;
import com.KambaFlix.mapper.MovieMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kambaflix/movie")
@RequiredArgsConstructor
public class MovieController {

    private  final MovieService movieService;

    @GetMapping()
    public ResponseEntity<List<MovieResponse>> findAll(){
        List<MovieResponse> movies = movieService.findAll()
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
        return ResponseEntity.ok(movies);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<MovieResponse>  SavedMovie(@RequestBody MovieRequest request){
        Movie movie = movieService.SavedMovie(MovieMapper.toMovie(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(MovieMapper.toMovieResponse(movie));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        if (movieService.findById(id) != null){
            Movie movieS = movieService.findById(id);
            return ResponseEntity.ok(movieS);
        }else{
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("A Streaming com IDs: "+id+" nao encontrado nos nossos banco de dados");
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> alterByMovie(@PathVariable Long id,@RequestBody MovieRequest request){
        return movieService.alterByMovie(id,MovieMapper.toMovie(request))
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie))).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam Long categoryId){
            return ResponseEntity.ok(movieService.findByCategory(categoryId)
                    .stream()
                    .map(MovieMapper::toMovieResponse)
                    .toList());
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteByCategoryId(@PathVariable Long id){
        if (movieService.findById(id) != null) {
            movieService.deleteByCategoryId(id);
            return ResponseEntity.ok("Categoria com IDs: "+id+" excluido com sucesso");
        }else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("A categoria com IDs: "+id+" nao encontrado nos nossos banco de dados");
        }
    }
}
