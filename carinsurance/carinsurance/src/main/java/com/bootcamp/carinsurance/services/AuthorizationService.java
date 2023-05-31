package com.bootcamp.carinsurance.services;

import com.bootcamp.carinsurance.dto.AuthenticationDTO;
import com.bootcamp.carinsurance.models.User;
import com.bootcamp.carinsurance.pojo.ResponseWithMessage;
import com.bootcamp.carinsurance.repository.UserRepository;
import com.bootcamp.carinsurance.security.JWTUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.security.auth.message.AuthException;
import java.util.Map;

@Component
public class AuthorizationService {
    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthorizationService(UserRepository userRepository, JWTUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseWithMessage performLogin(AuthenticationDTO authenticationDTO) throws AuthException {
        try {
            User user = userRepository.findByLogin(authenticationDTO.getLogin()).orElseThrow(() -> new AuthException("Пользователь не найден"));

            if(authenticationDTO.getLogin().equals(user.getLogin()) && passwordEncoder.matches(authenticationDTO.getPassword(),user.getPassword())){
                String token = jwtUtil.generateToken(authenticationDTO.getLogin());
                return new ResponseWithMessage(token);
            }else {
                return new ResponseWithMessage("Incorrect credentials");
            }
        }catch (AuthException e){
            return new ResponseWithMessage("Incorrect credentials");
        }
    }
}
