package com.KambaFlix.Controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public record MovieRequest (String title,
                            String description,

                            @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/mm/yyyy")
                            LocalDate releasedate,

                            double rating,
                            List<Long> category,
                            List<Long> streaming){
}
