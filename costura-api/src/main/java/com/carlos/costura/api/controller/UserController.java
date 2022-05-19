package com.carlos.costura.api.controller;

import com.carlos.costura.domain.exception.PageNotFoundException;
import com.carlos.costura.domain.model.Post;
import com.carlos.costura.domain.model.Purchase;
import com.carlos.costura.domain.model.SaleItem;
import com.carlos.costura.domain.model.User;
import com.carlos.costura.domain.model.dto.AuthorityDTO;
import com.carlos.costura.domain.model.dto.MessageDTO;
import com.carlos.costura.domain.model.dto.RegistrationForm;
import com.carlos.costura.domain.model.dto.UserOutput;
import com.carlos.costura.domain.model.pk.PostItemPK;
import com.carlos.costura.domain.model.pk.SaleItemPK;
import com.carlos.costura.domain.repository.*;
import com.carlos.costura.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
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

    private PurchaseRepository purchaseRepository;

    private PostRepository postRepository;

    private PostItemRepository postItemRepository;

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

    @GetMapping("/users/{userId}/post/{postId}/item/{postItem}")
    public boolean userHasBought(@PathVariable Long userId,@PathVariable Long postId,@PathVariable Integer postItem){
       User user = userRepository.findById(userId).orElseThrow(()-> new PageNotFoundException("Usuário não encontrado"));
       boolean hasBought = false;

        boolean exists = postItemRepository.existsByPostItemPK_Post_IdAndPostItemPK_Item(postId,postItem);
        if(exists){
            List<Purchase> purchaseList = user.getPurchaseList();
            for (Purchase p : purchaseList) {
                List<SaleItem> itemList = p.getItems();
                for (SaleItem i : itemList) {
                    if (i.getSaleItemPK().getPost().getId().equals(postId) && i.getSaleItemPK().getItem().equals(postItem)){
                        hasBought = true;
                    }
                }

            }
        }else{
            throw new PageNotFoundException("Post e item não encontrado");
        }

       return hasBought;
    }

    @PostMapping("/users/{id}/follow")
    public void followUser(@PathVariable Long id){
        userService.followUser(id);
    }

    @PostMapping("/contact")
    public void contactTeam(@RequestBody MessageDTO messageDTO) throws MessagingException, IOException {
        userService.notifyTeam(messageDTO);
    }

    @PostMapping("/password")
    public void passwordChangeRequest(@RequestBody MessageDTO messageDTO) throws MessagingException, IOException {
        User user = userRepository.findByEmail(messageDTO.getMessage()).orElseThrow(() -> new PageNotFoundException("Email não existe."));
        userService.notifyPasswordChangeRequest(messageDTO,user);

    }

    @PutMapping("/users/{userId}/password")
    public void changePassword(@PathVariable Long userId,@RequestBody MessageDTO messageDTO) throws MessagingException, IOException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = userRepository.findById(userId).orElseThrow(() -> new PageNotFoundException("Usuário não encontrado."));
        String password = encoder.encode(messageDTO.getMessage());
        user.setPassword(password);
        userRepository.save(user);
    }
}
