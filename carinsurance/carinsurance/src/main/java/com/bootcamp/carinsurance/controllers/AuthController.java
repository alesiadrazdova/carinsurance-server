package com.bootcamp.carinsurance.controllers;

import com.bootcamp.carinsurance.dto.AuthenticationDTO;
import com.bootcamp.carinsurance.dto.PersonDTO;
import com.bootcamp.carinsurance.models.Person;
import com.bootcamp.carinsurance.models.Role;
import com.bootcamp.carinsurance.security.JWTUtil;
import com.bootcamp.carinsurance.services.PersonService;
import com.bootcamp.carinsurance.services.RegistrationService;
import com.bootcamp.carinsurance.services.RoleService;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.message.AuthException;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JWTUtil jwtUtil;
    private final RegistrationService registrationService;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final PersonService personService;

    private final PasswordEncoder passwordEncoder;


    public AuthController(JWTUtil jwtUtil, RegistrationService registrationService, RoleService roleService, AuthenticationManager authenticationManager, PersonService personService, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.registrationService = registrationService;
        this.roleService = roleService;
        this.authenticationManager = authenticationManager;
        this.personService = personService;
        this.passwordEncoder = passwordEncoder;
    }

    //@PostMapping("/registration")
    @RequestMapping(value ="/registration", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> performRegistration(@RequestBody PersonDTO personDTO){
        Person person=new Person();
        person.setFirstName(personDTO.getFirstName());
        person.setLastName(personDTO.getFirstName());
        person.setLogin(personDTO.getLogin());
        person.setPassword(personDTO.getPassword());
        Role role=roleService.searchById(personDTO.getRoleId());
        person.setRole(role);
        registrationService.register(person,role);
        String token=jwtUtil.generateToken(person.getLogin());
        return Map.of("jwt-token",token);
    }
    //  @PostMapping("/login")
    @RequestMapping(value ="/login", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,String> performLogin(@RequestBody AuthenticationDTO authenticationDTO) throws AuthException {
        try {
            Person person = personService.findUserByLogin(authenticationDTO.getLogin()).orElseThrow(() -> new AuthException("Пользователь не найден"));
            if(authenticationDTO.getLogin().equals(person.getLogin()) && passwordEncoder.matches(authenticationDTO.getPassword(),person.getPassword())){
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

