package com.bootcamp.carinsurance.config;

import com.bootcamp.carinsurance.filters.JWTFilter;
import com.bootcamp.carinsurance.repository.UserRepository;
import com.bootcamp.carinsurance.security.JWTUtil;
import com.bootcamp.carinsurance.services.UserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private UserDetailsService userDetailsService;
    private final JWTFilter jwtFilter;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService,JWTFilter jwtFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/client").hasRole("Client")
                .antMatchers("/insurance_agency").hasRole("Insurance agency")
                .antMatchers("/estimator").hasRole("Estimator")
                .antMatchers("/auth/login", "/auth/registration", "/error", "/api/hello", "/show").permitAll()
                .anyRequest().hasAnyRole("Client", "Insurance agency", "Estimator")
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager()
            throws Exception {
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(getPasswordEncoder());
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new JWTTokenEncoderProxy();
    }
}

