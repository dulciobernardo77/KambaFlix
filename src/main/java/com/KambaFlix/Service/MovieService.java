package com.KambaFlix.Service;

import com.KambaFlix.Entity.Category;
import com.KambaFlix.Entity.Movie;
import com.KambaFlix.Entity.Streaming;
import com.KambaFlix.Repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository repository;
    private  CategoryService categoryService;
    private  StreamingService streamingService;

    public MovieService(CategoryService categoryService, MovieRepository repository, StreamingService streamingService) {
        this.categoryService = categoryService;
        this.repository = repository;
        this.streamingService = streamingService;
    }

    public List<Movie> findAll() {
        return repository.findAll();
    }

     public  Movie SavedMovie(Movie movie){
        movie.setCategory(this.findCategory(movie.getCategory()));
        movie.setStreaming(this.findStreaming(movie.getStreaming()));
        return  repository.save(movie);
    }

    private List<Category> findCategory(List<Category> categories) {
        List<Category> categories1 = new ArrayList<>();

        categories.forEach(category -> {
            Category foundCategory = categoryService.findById(category.getId());

            if (foundCategory != null) {
                categories1.add(foundCategory);
            }
        });

        return categories1;
    }

    private List<Streaming> findStreaming(List<Streaming> streamings) {
        List<Streaming> streamings1 = new ArrayList<>();

        streamings.forEach(streaming -> {
            Streaming foundStreaming = streamingService.findById(streaming.getId());

            if (foundStreaming != null) {
                streamings1.add(foundStreaming);
            }
        });

        return streamings1;
    }

    public Movie findById(Long id){
     Optional<Movie> movieOptional = repository.findById(id);
     return movieOptional.orElse(null);
    }

    public Movie alterByMovie(Long id,Movie movie){
        Optional<Movie> movieOptional = repository.findById(id);
        if (movieOptional.isPresent()){
            return repository.save(movie);
        }
        return null;
    }

    public void deleteByCategoryId(Long id){
        repository.deleteById(id);
    }
}
