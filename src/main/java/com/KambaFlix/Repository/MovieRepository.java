package com.KambaFlix.Repository;

import com.KambaFlix.Entity.Category;
import com.KambaFlix.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findMovieByCategory(List<Category> category);

}
