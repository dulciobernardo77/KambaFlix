package com.KambaFlix.Controller.response;


import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieResponse(Long id,
                            String title,
                            String description,
                            LocalDate releasedate,
                            double rating,
                            List<CategoryResponse> category,
                            List<StreamingResponse> streaming) {
}
