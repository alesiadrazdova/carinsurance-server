package com.bootcamp.carinsurance.services;

import com.bootcamp.carinsurance.dto.UserDTO;
import com.bootcamp.carinsurance.models.User;
import com.bootcamp.carinsurance.models.Role;
import com.bootcamp.carinsurance.repository.RoleRepository;
import com.bootcamp.carinsurance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collections;

@Component
public class RegistrationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(User user, Role role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role);
        userRepository.save(user);
    }
    public ResponseEntity<?> performRegistration(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getFirstName());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        Role role = roleRepository.findById(userDTO.getRoleId()).orElse(null);
        user.setRole(role);
        register(user, role);
        return ResponseEntity.ok(Collections.singletonMap("message","Registration successful"));
    }
}
