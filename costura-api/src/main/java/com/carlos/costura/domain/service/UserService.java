package com.carlos.costura.domain.service;

import com.carlos.costura.domain.model.User;
import com.carlos.costura.domain.model.dto.UserForm;
import com.carlos.costura.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public User save(UserForm user){
         User modelUser = User.toModel(user);
        return userRepository.save(modelUser);
    }
}
