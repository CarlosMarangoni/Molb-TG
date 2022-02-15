package com.carlos.costura.domain.service;

import com.carlos.costura.domain.exception.AuthorizationException;
import com.carlos.costura.domain.exception.PageNotFoundException;
import com.carlos.costura.domain.model.Role;
import com.carlos.costura.domain.model.User;
import com.carlos.costura.domain.model.dto.RegistrationForm;
import com.carlos.costura.domain.model.enumeration.RoleName;
import com.carlos.costura.domain.repository.RoleRepository;
import com.carlos.costura.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private S3Service s3Service;

    @Transactional
    public User save(RegistrationForm user, MultipartFile imageFile){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User modelUser = User.toModel(user);
        if(imageFile != null){
            modelUser.setProfileImage(uploadProfilePicture(imageFile).toString());
        }else{
            modelUser.setProfileImage("");
        }
        modelUser.setPassword(encoder.encode(modelUser.getPassword()));
        Set<String> strRoles = new HashSet<>();
        strRoles = user.getRoles();
        Set<Role> roles = new HashSet<>();
        if (!strRoles.isEmpty()) {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Falha! -> Causa: Permissão não encontrada."));
                        roles.add(adminRole);

                        break;

                    case "creator":
                        Role creatorRole = roleRepository.findByName(RoleName.ROLE_CREATOR)
                                .orElseThrow(() -> new RuntimeException("Falha! -> Causa: Permissão não encontrada."));
                        roles.add(creatorRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Falha! -> Causa: Permissão não encontrada."));
                        roles.add(userRole);
                }
            });
        }else {
            Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Falha! -> Causa: Permissão não encontrada."));
            roles.add(userRole);
        }
        modelUser.setRoles(roles);

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

    public User updateUserDesc(Long id,String description) {
        User user = userRepository.findById(id).orElseThrow(() -> new PageNotFoundException("Página não encontrada."));
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(loggedUser.getId().equals(user.getId())){
            user.setDescription(description);
        }else{
            throw new AuthorizationException("Acesso negado.");
        }

        return userRepository.save(user);
    }
}
