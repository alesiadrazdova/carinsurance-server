package com.bootcamp.carinsurance.filters;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bootcamp.carinsurance.security.JWTUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    public JWTFilter(JWTUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = jwtUtil.resolveToken(request);
        if (token != null) {
            try {
                String username = jwtUtil.validateTokenAndRetrieveClaimLogin(token);
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    Authentication authentication = new UsernamePasswordAuthenticationToken(username, null);
                    authentication = authenticationManager.authenticate(authentication);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
                filterChain.doFilter(request, response);
            } catch (JWTVerificationException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        } else {
            filterChain.doFilter(request, response);
            return;
        }
    }
}
