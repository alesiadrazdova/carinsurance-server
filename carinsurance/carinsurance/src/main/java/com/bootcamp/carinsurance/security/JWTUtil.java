package com.bootcamp.carinsurance.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JWTUtil {
    @Value("${jwt_secret}")
    private String secretWord;
    public String generateToken(String login){
        Date expirationDate = Date.from(ZonedDateTime.now().plusMonths(2).toInstant());
        return JWT.create().withSubject("Person details").
                withClaim("login",login).
                withIssuer("authService").
                withIssuedAt(new Date()).
                withExpiresAt(expirationDate).
                sign(Algorithm.HMAC256(secretWord));
    }
    public String validateToken(String token) throws JWTVerificationException {
        JWTVerifier verifier= JWT.require(Algorithm.HMAC256(secretWord)).
                withSubject("Person details").
                withIssuer("authService").
                build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("login").asString();
    }
}
