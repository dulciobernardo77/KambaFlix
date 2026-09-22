package com.KambaFlix.Controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.List;

public record MovieRequest (@NotEmpty(message = "O titulo do filme e obrigatorio") String title,
                            String description,

                            @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/mm/yyyy")
                            LocalDate releasedate,

                            double rating,
                            List<Long> category,
                            List<Long> streaming){
}
