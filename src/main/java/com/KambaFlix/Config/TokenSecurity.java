package com.KambaFlix.Config;


import com.KambaFlix.Entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TokenSecurity {

    @Value("${kambaflix.security.secret}")
    private String secret;

    public  String generateToken(User user){

        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("userId",user.getId())
                .withClaim("name",user.getName())
                .withIssuedAt(Instant.now().plusSeconds(86400))
                .withExpiresAt(Instant.now())
                .withIssuer("API Kambaflix")
                .sign(algorithm);
    }

}
