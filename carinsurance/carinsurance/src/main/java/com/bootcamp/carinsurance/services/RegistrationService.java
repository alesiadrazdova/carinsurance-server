package com.bootcamp.carinsurance.services;

import com.bootcamp.carinsurance.dto.AuthenticationDTO;
import com.bootcamp.carinsurance.models.User;
import com.bootcamp.carinsurance.models.Role;
import com.bootcamp.carinsurance.pojo.ResponseWithMessage;
import com.bootcamp.carinsurance.repository.UserRepository;
import com.bootcamp.carinsurance.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.security.auth.message.AuthException;
import javax.transaction.Transactional;
import java.util.Map;

@Component
public class RegistrationService {
    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserRepository userRepository, JWTUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(User user, Role role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role);
        userRepository.save(user);
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
