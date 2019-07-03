package com.oksana.library.controllers;

import com.oksana.library.config.TokenHelper;
import com.oksana.library.dtos.UserDto;
import com.oksana.library.dtos.UserLoginRequestDto;
import com.oksana.library.entities.User;
import com.oksana.library.mappers.UserMapper;
import com.oksana.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;
    private final TokenHelper tokenHelper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserMapper userMapper, UserService userService, TokenHelper tokenHelper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userService = userService;
        this.tokenHelper = tokenHelper;
        this.passwordEncoder = passwordEncoder;
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
        User deletedUser = this.userService.findById(id).orElseThrow(EntityNotFoundException::new);
       this.userService.delete(deletedUser);
       return "User "+deletedUser.getUsername()+ " has been deleted";
    }

    @GetMapping
    public Page<UserDto> findAllUsers(Pageable pageable){
        Page<User> users = this.userService.findAll(pageable);
        return users.map(this.userMapper::mapToDto);
    }

    @GetMapping("/search")
    public UserDto findByUserName( @RequestParam(value = "username") String username){
        User user = this.userService.findByUsername(username).orElseThrow(EntityNotFoundException::new);
        return this.userMapper.mapToDto(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequestDto userLoginRequestDto){
//        User foundedUser = this.userService.findByUsername(userLoginRequestDto.getUsername())// находим user в Бд по имени
//                            .filter(user -> BCrypt.checkpw(userLoginRequestDto.getPassword(), user.getUserPassword()))// фильтрация найденного user по хешам пароля
//                            .orElseThrow(() -> new EntityNotFoundException("Incorrect login or password"));// исключения в случае неуспешного прохлждения одного или всех этапов
//        return this.tokenHelper.tokenFor(foundedUser);

        User foundedUser = this.userService.findByUsername(userLoginRequestDto.getUsername()).orElseThrow(() -> new RuntimeException("User was not founded"));
        if(foundedUser.getUserPassword().equals(this.passwordEncoder.encode(userLoginRequestDto.getPassword()))){
            return this.tokenHelper.tokenFor(foundedUser);
        }else{
            throw new RuntimeException("Your password was not validated. Try again.");
        }
    }
}
