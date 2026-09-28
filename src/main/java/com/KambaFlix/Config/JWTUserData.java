package com.KambaFlix.Config;

import lombok.Builder;

@Builder
public record JWTUserData(Long id,String nome , String email) {
}
