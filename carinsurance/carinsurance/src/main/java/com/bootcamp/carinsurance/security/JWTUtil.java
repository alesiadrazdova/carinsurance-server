package com.bootcamp.carinsurance.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bootcamp.carinsurance.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JWTUtil {
    private final UserDetailsService userDetailsService;
    @Value("${jwt_secret}")
    private String secretWord;

    @Value("${jwt_expired}")
    private long timeOfExpirationInMinutes;
    private JWTVerifier verifier;
    @PostConstruct
    private void initVerifier() {
        verifier = JWT.require(Algorithm.HMAC256(secretWord)).
                withSubject("User details").
                withIssuer("authService").
                build();
    }

    public JWTUtil(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String generateToken(String login){
        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(timeOfExpirationInMinutes).toInstant());
        return JWT.create().withSubject("User details").
                withClaim("login",login).
                withIssuer("authService").
                withIssuedAt(new Date()).
                withExpiresAt(expirationDate).
                sign(Algorithm.HMAC256(secretWord));
    }
    public String validateTokenAndRetrieveClaimLogin(String token) throws JWTVerificationException {
        try {
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("login").asString();
        }catch (TokenExpiredException | IllegalArgumentException e){
            throw new TokenExpiredException("JWT token is expired", Instant.now());
        }
    }

    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken !=null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }

    public Authentication getAuthentication(String token){
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(validateTokenAndRetrieveClaimLogin(token));
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }
}
