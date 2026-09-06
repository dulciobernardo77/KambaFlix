package com.KambaFlix.Controller.request;

import java.time.LocalDate;
import java.util.List;

public record MovieRequest (String title,
                            String description,
                            LocalDate releasedate,
                            double rating,
                            List<Long> category,
                            List<Long> streaming){
}
