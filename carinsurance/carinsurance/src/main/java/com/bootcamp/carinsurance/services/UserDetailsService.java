package com.bootcamp.carinsurance.services;

import com.bootcamp.carinsurance.models.User;
import com.bootcamp.carinsurance.repository.UserRepository;
import com.bootcamp.carinsurance.security.UserDetails;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String login) throws BadCredentialsException {
        Optional<User> user = userRepository.findByLogin(login);
        if (user.isEmpty()) {
            throw new BadCredentialsException ("User Not Found!");
        }
        return new UserDetails(user.get());
    }
}
