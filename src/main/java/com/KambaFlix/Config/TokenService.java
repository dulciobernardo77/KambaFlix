package com.KambaFlix.Config;


import com.KambaFlix.Entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenService {

    @Value("${kambaflix.security.secret}")
    private String secret;

    public  String generateToken(User user){

        Algorithm algorithm = Algorithm.HMAC256(secret);


        Instant now = Instant.now();

        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("userId", user.getId())
                .withClaim("name", user.getName())
                .withIssuedAt(now)
                .withExpiresAt(now.plusSeconds(86400))
                .withIssuer("API Kambaflix")
                .sign(algorithm);
    }
    public Optional<JWTUserData> verifyToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT jwt = JWT.require(algorithm)
                    .build()
                    .verify(token);

            return Optional.of(JWTUserData
                    .builder()
                    .id(jwt.getClaim("userId").asLong())
                    .nome(jwt.getClaim("name").asString())
                    .email(jwt.getSubject())
                    .build());

        } catch (JWTVerificationException ex) {
            return Optional.empty();
        }
    }
}
