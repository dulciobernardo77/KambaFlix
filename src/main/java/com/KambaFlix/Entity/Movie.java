package com.KambaFlix.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name ="movie")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Movie {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @Column(nullable = false)
    private String title;

    private String description;

    @Column(name = "release_date")
    private LocalDate releasedate ;

    private  double rating;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdat ;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedat;

    @ManyToMany
    @JoinTable(name = "movie_category",
            joinColumns = @JoinColumn(name="movie_id"),
            inverseJoinColumns = @JoinColumn(name="category_id")
    )
    private List<Category> category;

    @ManyToMany
    @JoinTable(name = "movie_streaming",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "streaming_id")
    )
    private List<Streaming> streaming;

}
