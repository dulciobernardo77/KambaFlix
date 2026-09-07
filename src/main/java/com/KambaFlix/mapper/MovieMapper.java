package com.KambaFlix.mapper;

import com.KambaFlix.Controller.request.MovieRequest;
import com.KambaFlix.Controller.response.CategoryResponse;
import com.KambaFlix.Controller.response.MovieResponse;
import com.KambaFlix.Controller.response.StreamingResponse;
import com.KambaFlix.Entity.Category;
import com.KambaFlix.Entity.Movie;
import com.KambaFlix.Entity.Streaming;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper {

    public  static Movie toMovie(MovieRequest request){
        List<Category> categories = request.category()
                .stream()
                .map(category_id -> Category.builder().id(category_id).build())
                .toList();


        List<Streaming> streamings = request.streaming().stream()
                .map(Streaming_id -> Streaming.builder().id(Streaming_id).build())
                .toList();

        return Movie
                .builder()
                .title(request.title())
                .description(request.description())
                .rating(request.rating())
                .category(categories)
                .streaming(streamings)
                .build();
    }

    public static  MovieResponse toMovieResponse(Movie movie){
        List<CategoryResponse> categorys = movie.getCategory().stream()
                .map(category -> CategoryMapper.toCategoryResponce(category))
                .toList();

        List<StreamingResponse> streamings = movie.getStreaming().stream()
                .map(streaming -> StreamingMapper.toStreamingResponse(streaming))
                .toList();

        return MovieResponse
                .builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releasedate(movie.getReleasedate())
                .rating(movie.getRating())
                .category(categorys)
                .streaming(streamings)
                .build();
    }
}
