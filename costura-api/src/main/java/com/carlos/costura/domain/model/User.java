package com.carlos.costura.domain.model;

import com.carlos.costura.domain.exception.AuthorizationException;
import com.carlos.costura.domain.model.dto.RegistrationForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String user;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    @OneToMany(mappedBy = "buyer")
    private List<Purchase> purchaseList = new ArrayList<>();

    @OneToMany(mappedBy="followersPK.to")
    private List<Followers> followers;

    @OneToMany(mappedBy="followersPK.from")
    private List<Followers> following;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    private String profileImage;

    public User(String name, String user,String description,String email, String password) {
        this.name = name;
        this.user = user;
        this.description = description;
        this.email = email;
        this.password = password;
    }

    public User(Long id) {
        this.id= id;
    }

    public static User toModel(RegistrationForm registrationForm){
        return new User(
                registrationForm.getName(),
                registrationForm.getUsername(),
                registrationForm.getDescription(),
                registrationForm.getEmail(),
                registrationForm.getPassword());
    }

    public static User of(Long userId) {
        return new User(userId);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static User isAuthenticatedReturnUser(){
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedUser == null){
            throw new AuthorizationException("Acesso negado.");
        }
        return loggedUser;
    }

    public static Boolean isAuthenticated(){
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedUser == null){
            throw new AuthorizationException("Acesso negado.");
        }
        return Boolean.TRUE;
    }
}
