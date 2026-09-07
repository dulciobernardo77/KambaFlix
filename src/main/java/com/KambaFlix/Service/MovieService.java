package com.KambaFlix.Service;

import com.KambaFlix.Entity.Category;
import com.KambaFlix.Entity.Movie;
import com.KambaFlix.Entity.Streaming;
import com.KambaFlix.Repository.MovieRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository repository;
    private final  CategoryService categoryService;
    private final StreamingService streamingService;

    public List<Movie> findAll() {
        return repository.findAll();
    }

    public  Movie SavedMovie(Movie movie){
        movie.setCategory(this.findCategory(movie.getCategory()));
        movie.setStreaming(this.findStreaming(movie.getStreaming()));
        return  repository.save(movie);
    }

    private List<Category> findCategory(List<Category> categories) {
        return categories.stream()
                .map(category -> categoryService.findById(category.getId()))
                .filter(Objects::nonNull)
                .toList();
    }

    private List<Streaming> findStreaming(List<Streaming> streamings) {
        return streamings.stream()
                .map(streaming -> streamingService.findById(streaming.getId()))
                .filter(Objects::nonNull)
                .toList();
    }

    public Movie findById(Long id){
     Optional<Movie> movieOptional = repository.findById(id);
     return movieOptional.orElse(null);
    }

    public Optional<Movie> alterByMovie( Long id,Movie updatemovie){
        Optional<Movie> movieOptional = repository.findById(id);
        if (movieOptional.isPresent()){

            List<Category> categoryList = this.findCategory(updatemovie.getCategory());
            List<Streaming> streamingList = this.findStreaming(updatemovie.getStreaming());

            Movie movie = movieOptional.get();
            movie.setTitle(updatemovie.getTitle());
            movie.setDescription(updatemovie.getDescription());
            movie.setReleasedate(updatemovie.getReleasedate());
            movie.setRating(updatemovie.getRating());


            movie.getCategory().clear();
            movie.getCategory().addAll(categoryList);

            movie.getStreaming().clear();
            movie.getStreaming().addAll(streamingList);



            repository.save(movie);
            return Optional.of(movie);
        }
        return Optional.empty();
    }

    public  List<Movie> findByCategory(Long categoryId){
        return repository.findMovieByCategory(List.of(Category.builder().id(categoryId).build()));
    }

    public void deleteByCategoryId(Long id){
        repository.deleteById(id);
    }
}
