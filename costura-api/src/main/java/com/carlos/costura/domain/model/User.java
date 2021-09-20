package com.carlos.costura.domain.model;

import com.carlos.costura.domain.model.dto.UserForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String username;

    @Email
    private String email;

    private String password;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    public User(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public static User toModel(UserForm userForm){
        return new User(
                userForm.getName(),
                userForm.getUsername(),
                userForm.getEmail(),
                userForm.getPassword());
    }

}
