package com.carlos.costura.api.controller;

import com.carlos.costura.domain.model.User;
import com.carlos.costura.domain.model.dto.*;
import com.carlos.costura.domain.repository.UserRepository;
import com.carlos.costura.domain.security.jwt.JwtProvider;
import com.carlos.costura.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        User userDetails = (User)authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getAuthorities(), UserSummary.toOutput(userDetails)));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestPart("user") RegistrationForm signUpRequest,@RequestParam(name = "file",required = false) MultipartFile imageFile) {

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Falha! -> Email já está em uso!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = userService.save(signUpRequest,imageFile);
//        User user = new User(signUpRequest.getName(),signUpRequest.getUsername(), signUpRequest.getDescription(),signUpRequest.getEmail(),
//                encoder.encode(signUpRequest.getPassword()));




        return new ResponseEntity<>(new ResponseMessage("Usuário registrado com sucesso!"), HttpStatus.OK);
    }
}
