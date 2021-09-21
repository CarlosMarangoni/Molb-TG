package com.carlos.costura.domain.model;

import com.carlos.costura.domain.model.dto.UserForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public static User toModel(UserForm userForm){
        return new User(
                userForm.getName(),
                userForm.getUsername(),
                userForm.getEmail(),
                userForm.getPassword());
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
