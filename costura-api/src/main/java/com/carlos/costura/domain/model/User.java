package com.carlos.costura.domain.model;

import com.carlos.costura.domain.model.dto.LoginForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


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
    private String user;

    @Email
    private String email;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Purchase> purchaseList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Sale> saleList = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> followers = new LinkedList<>();

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Authorities> authorities = new ArrayList<>();

    public User(String name, String user, String email, String password) {
        this.name = name;
        this.user = user;
        this.email = email;
        this.password = password;
    }

    public User(Long id) {
        this.id= id;
    }

    public static User toModel(LoginForm loginForm){
        return new User(
                loginForm.getName(),
                loginForm.getUsername(),
                loginForm.getEmail(),
                loginForm.getPassword());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
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
}
