package com.bootcamp.carinsurance.controllers;

import com.bootcamp.carinsurance.dto.AuthenticationDTO;
import com.bootcamp.carinsurance.dto.UserDTO;
import com.bootcamp.carinsurance.models.User;
import com.bootcamp.carinsurance.models.Role;
import com.bootcamp.carinsurance.security.JWTUtil;
import com.bootcamp.carinsurance.services.UserService;
import com.bootcamp.carinsurance.services.RegistrationService;
import com.bootcamp.carinsurance.services.RoleService;

import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://127.0.0.1:3000")
public class AuthController {
    private final JWTUtil jwtUtil;
    private final RegistrationService registrationService;
    private final RoleService roleService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(JWTUtil jwtUtil, RegistrationService registrationService, RoleService roleService, UserService userService, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.registrationService = registrationService;
        this.roleService = roleService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value ="/registration", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> performRegistration(@RequestBody UserDTO userDTO){
        User user =new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getFirstName());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        Role role=roleService.searchById(userDTO.getRoleId());
        user.setRole(role);
        registrationService.register(user,role);
        String token=jwtUtil.generateToken(user.getLogin());
        return Map.of("jwt-token",token);
    }

    @RequestMapping(value ="/login", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,String> performLogin(@RequestBody AuthenticationDTO authenticationDTO) throws AuthException {
        try {
            User user = userService.findUserByLogin(authenticationDTO.getLogin()).orElseThrow(() -> new AuthException("Пользователь не найден"));
            if(authenticationDTO.getLogin().equals(user.getLogin()) && passwordEncoder.matches(authenticationDTO.getPassword(), user.getPassword())){
                String token = jwtUtil.generateToken(authenticationDTO.getLogin());
                return Map.of("jwt-token",token);
            }else {
                return Map.of("message","Incorrect credentials");
            }
        }catch (AuthException e){
            return Map.of("message","Incorrect credentials");
        }
    }
}
