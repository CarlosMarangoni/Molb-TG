package com.carlos.costura.domain.service;

import com.carlos.costura.domain.model.User;
import com.carlos.costura.domain.model.dto.LoginForm;
import com.carlos.costura.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public User save(LoginForm user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User modelUser = User.toModel(user);
        String encodedPassword = encoder.encode(modelUser.getPassword());
        modelUser.setPassword(encodedPassword);
        return userRepository.save(modelUser);
    }

    public void followUser(Long id) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User followedUser = userRepository.findById(id).get();
        followedUser.getFollowers().add(loggedUser);
        userRepository.save(followedUser);
    }
}
