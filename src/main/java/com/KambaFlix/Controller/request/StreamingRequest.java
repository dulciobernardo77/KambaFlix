package com.KambaFlix.Controller.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record StreamingRequest(@NotEmpty(message = "O nome do Servico de streaming e obrigatorio") String nome) {
}
