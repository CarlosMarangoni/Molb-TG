package com.carlos.costura.api.controller;

import com.carlos.costura.domain.exception.PageNotFoundException;
import com.carlos.costura.domain.model.User;
import com.carlos.costura.domain.model.dto.LoginForm;
import com.carlos.costura.domain.model.dto.UserOutput;
import com.carlos.costura.domain.repository.UserRepository;
import com.carlos.costura.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping
public class UserController {

    private UserRepository userRepository;

    private UserService userService;

    @GetMapping("/users/{id}")
    public ResponseEntity<UserOutput> getUser(@PathVariable Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new PageNotFoundException("Página não encontrada."));
        return ResponseEntity.ok().body(UserOutput.toOutput(user));

    }

    @PostMapping("/register")
    public ResponseEntity<User> addUser(@Valid @RequestBody LoginForm loginForm, UriComponentsBuilder uriComponentsBuilder){
        User createdUser = userService.save(loginForm);
        UriComponents uriComponents = uriComponentsBuilder.path("/users/{id}").buildAndExpand(createdUser.getId());
        var location = uriComponents.toUri();
        return ResponseEntity.created(location).body(createdUser);

    }

    @PostMapping("/users/{id}/follow")
    public void followUser(@PathVariable Long id){
        userService.followUser(id);
    }
}
