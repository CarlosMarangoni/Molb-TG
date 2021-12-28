package com.carlos.costura.api.controller;

import com.carlos.costura.domain.exception.PageNotFoundException;
import com.carlos.costura.domain.model.User;
import com.carlos.costura.domain.model.dto.LoginForm;
import com.carlos.costura.domain.model.dto.UserOutput;
import com.carlos.costura.domain.repository.UserRepository;
import com.carlos.costura.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping
public class UserController {

    private UserRepository userRepository;

    private UserService userService;

    @GetMapping("/login")
    public String login(){
        return "Login realizado com sucesso.";
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserOutput>> getUsers(){
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users.stream().map(UserOutput::toOutput).collect(Collectors.toList()));

    }

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

    @PostMapping("/users/picture")
    public ResponseEntity<Void> uploadPicture(@RequestParam(name = "file") MultipartFile file){
        User loggedUser = User.isAuthenticatedReturnUser();
        loggedUser = userRepository.findById(loggedUser.getId()).get();
        URI uri = userService.uploadProfilePicture(file);
        loggedUser.setProfileImage(uri.toString());
        userRepository.save(loggedUser);

        return ResponseEntity.created(uri).build();

    }

    @PostMapping("/users/{id}/follow")
    public void followUser(@PathVariable Long id){
        userService.followUser(id);
    }
}
