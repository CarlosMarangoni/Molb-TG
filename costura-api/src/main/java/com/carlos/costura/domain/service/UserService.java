package com.carlos.costura.domain.service;

import com.carlos.costura.domain.exception.PageNotFoundException;
import com.carlos.costura.domain.model.User;
import com.carlos.costura.domain.model.dto.LoginForm;
import com.carlos.costura.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    @Transactional
    public User save(LoginForm user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User modelUser = User.toModel(user);
        String encodedPassword = encoder.encode(modelUser.getPassword());
        modelUser.setPassword(encodedPassword);
        return userRepository.save(modelUser);
    }

    public void followUser(Long id) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User followedUser = userRepository.findById(id).orElseThrow(() -> new PageNotFoundException("Usuário não encontrado."));
        loggedUser = userRepository.findById(loggedUser.getId()).orElseThrow(() -> new PageNotFoundException("Usuário não encontrado."));

        if(loggedUser.getFollowing().stream().anyMatch(n -> n.getId() == followedUser.getId())){
            loggedUser.getFollowing().remove(followedUser);
            followedUser.getFollowers().remove(loggedUser);
        }else{
            loggedUser.getFollowing().add(followedUser);
            followedUser.getFollowers().add(loggedUser);
        }
        userRepository.save(followedUser);

    }
}
