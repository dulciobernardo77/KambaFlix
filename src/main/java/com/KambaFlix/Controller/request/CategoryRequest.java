package com.KambaFlix.Controller.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record CategoryRequest(@NotEmpty(message = "O Nome da categoria e obrigatorio") String nome) {

}
