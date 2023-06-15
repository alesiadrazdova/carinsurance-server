package com.bootcamp.carinsurance.services;

import com.bootcamp.carinsurance.dto.AuthResponseDTO;
import com.bootcamp.carinsurance.dto.AuthenticationDTO;
import com.bootcamp.carinsurance.security.JWTUtil;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorizationServiceTest {
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JWTUtil jwtUtil;


    @InjectMocks
    private AuthorizationService authorizationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void performLoginValidCredentialsReturnsSuccessfulResponse() {

        AuthenticationDTO authenticationDTO = new AuthenticationDTO("username", "password");
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_Client"));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authenticationDTO.getLogin(), authenticationDTO.getPassword());
        Collection grantedAuthorities = Lists.newArrayList(new SimpleGrantedAuthority("ROLE_Client"));
        Authentication authentication = mock(Authentication.class);

        when(authenticationManager.authenticate(authenticationToken)).thenReturn(authentication);
        when(authentication.getAuthorities()).thenReturn(grantedAuthorities);
        when(jwtUtil.generateToken(authenticationDTO.getLogin())).thenReturn("token");

        ResponseEntity<AuthResponseDTO> response = authorizationService.performLogin(authenticationDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Login successful", response.getBody().getMessage());
        assertEquals("[ROLE_Client]", response.getBody().getRole());

        verify(authenticationManager).authenticate(authenticationToken);
        verify(jwtUtil).generateToken(authenticationDTO.getLogin());
    }

    @Test
    void performLoginEmptyPasswordThrowsBadCredentialsException() {
        AuthenticationDTO authenticationDTO = new AuthenticationDTO("username", "");
        ResponseEntity<AuthResponseDTO> response = authorizationService.performLogin(authenticationDTO);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Incorrect credentials", response.getBody().getMessage());
        assertNull(response.getBody().getRole());

    }

    @Test
    void performLoginInvalidCredentialsReturnsUnauthorizedResponse() {

        AuthenticationDTO authenticationDTO = new AuthenticationDTO("username", "password");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authenticationDTO.getLogin(), authenticationDTO.getPassword());
        when(authenticationManager.authenticate(authenticationToken)).thenThrow(new BadCredentialsException("Invalid credentials"));

        ResponseEntity<AuthResponseDTO> response = authorizationService.performLogin(authenticationDTO);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Incorrect credentials", response.getBody().getMessage());
        assertNull(response.getBody().getRole());

        verify(authenticationManager).authenticate(authenticationToken);
    }

    void tearDown() {
        verifyNoMoreInteractions(authenticationManager, jwtUtil);
    }
}