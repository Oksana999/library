package com.oksana.library.services;

import com.oksana.library.entities.User;
import com.oksana.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

//    public User create(User user) {
//        user.setPasswordHash(this.passwordEncoder.encode(user.getPassword()));
//        return this.userRepository.save(user);
//    }
}
