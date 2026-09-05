package com.KambaFlix.Controller.response;

import lombok.Builder;

@Builder
public record CategoryResponse(Long id,String nome) {
}
