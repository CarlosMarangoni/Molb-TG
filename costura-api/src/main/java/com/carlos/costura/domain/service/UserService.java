package com.carlos.costura.domain.service;

import com.carlos.costura.domain.exception.AuthorizationException;
import com.carlos.costura.domain.exception.ConflictException;
import com.carlos.costura.domain.exception.PageNotFoundException;
import com.carlos.costura.domain.model.*;
import com.carlos.costura.domain.model.dto.RegistrationForm;
import com.carlos.costura.domain.model.enumeration.PaymentMethod;
import com.carlos.costura.domain.model.enumeration.RoleName;
import com.carlos.costura.domain.model.pk.FollowersPK;
import com.carlos.costura.domain.model.pk.SaleItemPK;
import com.carlos.costura.domain.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private SaleItemRepository saleItemRepository;

    private S3Service s3Service;

    private CartRepository cartRepository;

    private FollowersRepository followersRepository;

    private PurchaseRepository purchaseRepository;

    @Transactional
    public User save(RegistrationForm user, MultipartFile imageFile) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User modelUser = User.toModel(user);
        if (imageFile != null) {
            modelUser.setProfileImage(uploadProfilePicture(imageFile).toString());
        } else {
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
        } else {
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

        List<Followers> followers = loggedUser.getFollowers();
        if (loggedUser.getFollowing().stream().anyMatch(n -> n.getFollowersPK().getTo().getId() == followedUser.getId())) {
            followersRepository.deleteById(new FollowersPK(loggedUser,followedUser));
        } else {
            followersRepository.save(new Followers(new FollowersPK(loggedUser,followedUser)));
        }

    }

    public URI uploadProfilePicture(MultipartFile multipartFile) {
        return s3Service.uploadFile(multipartFile);
    }

    public User updateUserDesc(Long id, String description) {
        User user = userRepository.findById(id).orElseThrow(() -> new PageNotFoundException("Página não encontrada."));
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedUser.getId().equals(user.getId())) {
            user.setDescription(description);
        } else {
            throw new AuthorizationException("Acesso negado.");
        }

        return userRepository.save(user);
    }

    public boolean  buy(Purchase purchase, User loggedUser) {
        loggedUser = userRepository.findById(loggedUser.getId()).get();
        List<Purchase> purchaseList = loggedUser.getPurchaseList();
        List<SaleItem> boughtItems = new ArrayList<>();
        List<SaleItem> itemsToBuy = new ArrayList<>();

        for (Purchase p : purchaseList) {
            for (SaleItem i : p.getItems()) {
                boughtItems.add(i);
            }
        }
        itemsToBuy = purchase.getItems();

        for (SaleItem i : itemsToBuy) {
            if (saleItemRepository.existsBySaleItemPK_PostAndAndSaleItemPK_ItemAndSaleItemPK_Purchase_Buyer(i.getSaleItemPK().getPost(),i.getSaleItemPK().getItem(),loggedUser)) {
                throw new ConflictException("Você já comprou o item " + i.getSaleItemPK().getItem() + " da postagem: " + i.getSaleItemPK().getPost().getId());
            }
        }
        cartRepository.save(purchase);
        
        return true;
        }
}

