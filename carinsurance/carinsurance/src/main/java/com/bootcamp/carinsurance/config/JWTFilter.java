package com.bootcamp.carinsurance.config;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.bootcamp.carinsurance.security.JWTUtil;
import com.bootcamp.carinsurance.services.PersonDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final PersonDetailsService personDetailsServiceService;

    public JWTFilter(JWTUtil jwtUtil, PersonDetailsService personDetailsServiceService) {
        this.jwtUtil = jwtUtil;
        this.personDetailsServiceService = personDetailsServiceService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if(authHeader !=null && !authHeader.isBlank()&& authHeader.startsWith("Bearer ")){
            String jwt = authHeader.substring(7);
            if(jwt.isBlank()){
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                        "Invalid JWT Token in Bearer Header");
            }else {
                try {

                    String login = jwtUtil.validateToken(jwt);
                    UserDetails userDetails = personDetailsServiceService.loadUserByUsername(login);
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,userDetails.getPassword(),
                            userDetails.getAuthorities());
                    if (SecurityContextHolder.getContext().getAuthentication() == null) {
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }catch (JWTVerificationException exc){
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Invalid JWT Token");
                }
            }
        }

        filterChain.doFilter(request,response);
    }
}
