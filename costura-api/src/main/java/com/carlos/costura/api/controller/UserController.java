package com.carlos.costura.api.controller;

import com.carlos.costura.domain.exception.PageNotFoundException;
import com.carlos.costura.domain.model.User;
import com.carlos.costura.domain.model.dto.AuthorityDTO;
import com.carlos.costura.domain.model.dto.RegistrationForm;
import com.carlos.costura.domain.model.dto.UserOutput;
import com.carlos.costura.domain.repository.RoleRepository;
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

    private RoleRepository roleRepository;

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

    @GetMapping("/roles")
    public ResponseEntity<?> getAllRoles(){
        return ResponseEntity.ok().body(roleRepository.findAll());
    }

    @PutMapping("/users/description/{id}")
    public ResponseEntity<UserOutput> updateUserDesc(@PathVariable Long id,@RequestBody String description){
        User user = userService.updateUserDesc(id,description);
        return ResponseEntity.ok().body(UserOutput.toOutput(user));

    }

    @PutMapping("/users/roles/{id}")
    public boolean updateUserPermissions(@PathVariable Long id,@RequestBody AuthorityDTO permissions){
        boolean saved = userService.updateUserPermissions(id,permissions);
        return saved;
    }

    @PostMapping("/users/{id}/follow")
    public void followUser(@PathVariable Long id){
        userService.followUser(id);
    }
}
