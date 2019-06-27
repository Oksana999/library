package com.oksana.library.services;

import com.oksana.library.dtos.UserDto;
import com.oksana.library.entities.Book;
import com.oksana.library.entities.User;
import com.oksana.library.mappers.UserMapper;
import com.oksana.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

   public User create(User user) {
//       String md5pass = DigestUtils.md5DigestAsHex(user.getUserPassword().getBytes());
//       user.setUserPassword(md5pass);
       user.setUserPassword(this.passwordEncoder.encode(user.getUserPassword()));
       user.setCreatedAt(LocalDateTime.now());
       return this.userRepository.save(user);
   }
//        user.setPasswordHash(this.passwordEncoder.encode(user.getPassword()));
//        return this.userRepository.save(user);
//    }

   public Optional<User> findById(Long id){
        return this.userRepository.findById(id);
    }

    public User update(User user){
        return this.userRepository.save(user);
    }

    public void delete(User user){
        this.userRepository.delete(user);
    }

    public Page<User> findAll(Pageable pageable){
       return this.userRepository.findAll(pageable);
    }
}
