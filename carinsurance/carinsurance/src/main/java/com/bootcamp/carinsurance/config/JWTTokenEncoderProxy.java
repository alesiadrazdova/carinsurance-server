package com.bootcamp.carinsurance.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class JWTTokenEncoderProxy implements PasswordEncoder {
    private final PasswordEncoder passwordEncoder;

    public JWTTokenEncoderProxy(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if(rawPassword==null)
            return true;
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
