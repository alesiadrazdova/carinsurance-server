package com.bootcamp.carinsurance.controllers;

import com.bootcamp.carinsurance.dto.AuthenticationDTO;
import com.bootcamp.carinsurance.dto.UserDTO;
import com.bootcamp.carinsurance.services.AuthorizationService;
import com.bootcamp.carinsurance.services.RegistrationService;

import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final RegistrationService registrationService;
    private final AuthorizationService authorizationService;
    private AuthenticationManager authenticationManager;

    public AuthController(RegistrationService registrationService, AuthorizationService authorizationService) {
        this.registrationService = registrationService;
        this.authorizationService = authorizationService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> performRegistration(@RequestBody UserDTO userDTO) {
        return registrationService.performRegistration(userDTO);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> performLogin(@RequestBody AuthenticationDTO authenticationDTO) throws BadCredentialsException {
        return authorizationService.performLogin(authenticationDTO);
    }
}
