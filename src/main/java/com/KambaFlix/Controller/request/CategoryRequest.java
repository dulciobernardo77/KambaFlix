package com.KambaFlix.Controller.request;

import jakarta.persistence.Column;
import lombok.Builder;

@Builder
public record CategoryRequest(String nome) {

}
