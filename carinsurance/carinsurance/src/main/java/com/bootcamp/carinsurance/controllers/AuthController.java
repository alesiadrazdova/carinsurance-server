package com.bootcamp.carinsurance.controllers;

import com.bootcamp.carinsurance.dto.AuthenticationDTO;
import com.bootcamp.carinsurance.dto.UserDTO;
import com.bootcamp.carinsurance.models.User;
import com.bootcamp.carinsurance.models.Role;
import com.bootcamp.carinsurance.pojo.ResponseWithMessage;
import com.bootcamp.carinsurance.security.JWTUtil;
import com.bootcamp.carinsurance.services.AuthorizationService;
import com.bootcamp.carinsurance.services.RegistrationService;
import com.bootcamp.carinsurance.services.RoleService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JWTUtil jwtUtil;
    private final RegistrationService registrationService;
    private final RoleService roleService;
    private final AuthorizationService authorizationService;

    public AuthController(JWTUtil jwtUtil, RegistrationService registrationService, RoleService roleService, AuthorizationService authorizationService) {
        this.jwtUtil = jwtUtil;
        this.registrationService = registrationService;
        this.roleService = roleService;
        this.authorizationService = authorizationService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseWithMessage performRegistration(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getFirstName());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        Role role=roleService.searchById(userDTO.getRoleId());
        user.setRole(role);
        registrationService.register(user, role);
        String token = jwtUtil.generateToken(user.getLogin());
        return new ResponseWithMessage(token);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseWithMessage performLogin(@RequestBody AuthenticationDTO authenticationDTO) throws AuthException {
        return authorizationService.performLogin(authenticationDTO);
    }
}
