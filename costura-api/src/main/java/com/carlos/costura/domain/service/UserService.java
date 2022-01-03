package com.carlos.costura.domain.service;

import com.carlos.costura.domain.exception.PageNotFoundException;
import com.carlos.costura.domain.model.User;
import com.carlos.costura.domain.model.dto.LoginForm;
import com.carlos.costura.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.net.URI;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private S3Service s3Service;

    @Transactional
    public User save(LoginForm user,MultipartFile imageFile){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User modelUser = User.toModel(user);
        if(imageFile != null){
            modelUser.setProfileImage(uploadProfilePicture(imageFile).toString());
        }else{
            modelUser.setProfileImage("");
        }
        String encodedPassword = encoder.encode(modelUser.getPassword());
        modelUser.setPassword(encodedPassword);
        return userRepository.save(modelUser);
    }

    public void followUser(Long id) {
        User loggedUser = User.isAuthenticatedReturnUser();
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

    public URI uploadProfilePicture(MultipartFile multipartFile)
    {
        return s3Service.uploadFile(multipartFile);
    }
}
