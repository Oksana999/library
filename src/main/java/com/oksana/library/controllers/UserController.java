package com.oksana.library.controllers;

import com.oksana.library.dtos.UserDto;
import com.oksana.library.entities.User;
import com.oksana.library.mappers.UserMapper;
import com.oksana.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;

    @Autowired
    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }
    @PostMapping("/create")
    public UserDto create (@RequestBody UserDto userDto){
        User user = this.userService.create(this.userMapper.mapToEntity(userDto));
        return this.userMapper.mapToDto(user);
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Long id){
        User user = this.userService.findById(id).orElseThrow(EntityNotFoundException::new);
       return this.userMapper.mapToDto(user);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto){
        User user = this.userService.findById(id).orElseThrow(EntityNotFoundException::new);
        User newUser = this.userMapper.mapToEntity(userDto);
        newUser.setId(id);
        this.userService.update(newUser);
        userDto.setId(id);
        return userDto;

    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id){
       // User deletedUser = this.userService.findById(id).orElseThrow(EntityNotFoundException::new);
       return  this.userService.delete(id);
    }

    @GetMapping
    public Page<UserDto> findAllUsers(Pageable pageable){
        Page<User> users = this.userService.findAll(pageable);
        return users.map(this.userMapper::mapToDto);
    }

    @GetMapping("/{username}")
    public UserDto findByUserName(@PathVariable String username){
        User user = this.userService.findByUsername(username).orElseThrow(EntityNotFoundException::new);
        return this.userMapper.mapToDto(user);
    }
}
