package com.bootcamp.carinsurance.services;

import com.bootcamp.carinsurance.models.User;
import com.bootcamp.carinsurance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findOne(int id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElse(null);
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void update(int id, User updatedUser) {
        updatedUser.setUserId(id);
        userRepository.save(updatedUser);
    }

    public Optional<User> findUserByLogin(String login) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByLogin(login);
        return user;
    }

    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
