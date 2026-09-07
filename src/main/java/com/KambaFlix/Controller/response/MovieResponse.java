package com.KambaFlix.Controller.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieResponse(Long id,
                            String title,
                            String description,

                            @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/mm/yyyy")
                            LocalDate releasedate,

                            double rating,
                            List<CategoryResponse> category,
                            List<StreamingResponse> streaming) {
}
