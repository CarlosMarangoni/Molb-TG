package com.carlos.costura.api.controller;

import com.carlos.costura.domain.model.User;
import com.carlos.costura.domain.model.dto.UserForm;
import com.carlos.costura.domain.repository.UserRepository;
import com.carlos.costura.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;

    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody UserForm userForm, UriComponentsBuilder uriComponentsBuilder){
        User createdUser = userService.save(userForm);
        UriComponents uriComponents = uriComponentsBuilder.path("/users/{id}").buildAndExpand(createdUser.getId());
        var location = uriComponents.toUri();
        return ResponseEntity.created(location).body(createdUser);

    }
}
