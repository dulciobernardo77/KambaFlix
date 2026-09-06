package com.KambaFlix.Service;

import com.KambaFlix.Entity.Movie;
import com.KambaFlix.Repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public List<Movie> findAll() {
        return repository.findAll();
    }
     public  Movie SavedMovie(Movie movie){
        return  repository.save(movie);
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
