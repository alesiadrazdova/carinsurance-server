package com.bootcamp.carinsurance.filters;

import com.bootcamp.carinsurance.security.JWTUtil;
import com.bootcamp.carinsurance.services.UserDetailsService;
import org.springframework.security.core.Authentication;
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
    private final UserDetailsService userDetailsServiceService;

    public JWTFilter(JWTUtil jwtUtil, UserDetailsService userDetailsServiceService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsServiceService = userDetailsServiceService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = jwtUtil.resolveToken(request);
        if(token!=null && jwtUtil.validateTokenAndRetrieveClaim(token)!=null){
            Authentication authentication = jwtUtil.getAuthentication(token);
            if(authentication!=null){
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
