package com.myshopping.api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.myshopping.api.model.user.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class TokenService {
    private static final String issuerKey = "Api My Purchases";

    @Value("${api.security.token.secret}")
    private String secret;
    public String generateToken(Users user){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Api My Purchases")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
           throw new RuntimeException("Error generating token");
        }
    }

    public String getSubject(String token){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("Api My Purchases")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception){
            throw new RuntimeException("Invalid token");
        }
    }

}
