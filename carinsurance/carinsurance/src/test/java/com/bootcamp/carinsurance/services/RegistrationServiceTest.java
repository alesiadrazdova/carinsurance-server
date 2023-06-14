package com.bootcamp.carinsurance.services;

import com.bootcamp.carinsurance.dto.UserDTO;
import com.bootcamp.carinsurance.models.Role;
import com.bootcamp.carinsurance.models.User;
import com.bootcamp.carinsurance.repository.RoleRepository;
import com.bootcamp.carinsurance.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RegistrationServiceTest {

    @InjectMocks
    private RegistrationService registrationService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPerformRegistrationSuccessfulRegistration() {

        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("John");
        userDTO.setLastName("John");
        userDTO.setLogin("john");
        userDTO.setPassword("password");
        userDTO.setRoleId(1);

        Role role = new Role();
        role.setRoleId(1);
        role.setName("ROLE_Client");

        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setRole(role);

        when(roleRepository.findById(userDTO.getRoleId())).thenReturn(Optional.of(role));
        when(userRepository.findByLogin(userDTO.getLogin())).thenReturn(null);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("hashedPassword");

        ResponseEntity<?> response = registrationService.performRegistration(userDTO);

        verify(roleRepository).findById(userDTO.getRoleId());
        verify(userRepository).findByLogin(userDTO.getLogin());
        verify(passwordEncoder).encode(user.getPassword());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.singletonMap("message", "Registration successful"), response.getBody());
    }

    @Test
    void testPerformRegistrationDuplicateUsername() {

        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("John");
        userDTO.setLastName("John");
        userDTO.setLogin("john");
        userDTO.setPassword("password");
        userDTO.setRoleId(1);

        Role role = new Role();
        role.setRoleId(1);
        role.setName("ROLE_Client");

        when(roleRepository.findById(userDTO.getRoleId())).thenReturn(Optional.of(role));
        when(userRepository.findByLogin(userDTO.getLogin())).thenReturn(Optional.of(new User()));

        ResponseEntity<?> response = registrationService.performRegistration(userDTO);

        verify(roleRepository).findById(userDTO.getRoleId());
        verify(userRepository).findByLogin(userDTO.getLogin());
        verify(userRepository, never()).save(any(User.class));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(Collections.singletonMap("error", "Username already exists"), response.getBody());
    }
}