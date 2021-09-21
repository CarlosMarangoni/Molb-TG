package com.carlos.costura.api.controller;

import com.carlos.costura.domain.model.User;
import com.carlos.costura.domain.model.dto.LoginForm;
import com.carlos.costura.domain.repository.UserRepository;
import com.carlos.costura.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@AllArgsConstructor
@RequestMapping
public class UserController {

    private UserRepository userRepository;

    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<User> addUser(@RequestBody LoginForm loginForm, UriComponentsBuilder uriComponentsBuilder){
        User createdUser = userService.save(loginForm);
        UriComponents uriComponents = uriComponentsBuilder.path("/users/{id}").buildAndExpand(createdUser.getId());
        var location = uriComponents.toUri();
        return ResponseEntity.created(location).body(createdUser);

    }
}
