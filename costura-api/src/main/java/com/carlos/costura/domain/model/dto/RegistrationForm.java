package com.carlos.costura.domain.model.dto;

import com.carlos.costura.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationForm {

    @NotEmpty
    private String username;

    @NotEmpty
    private String name;

    @NotNull
    private String description;

    @Email
    @NotNull
    private String email;

    @NotEmpty
    private String password;

    private Set<String> roles = new HashSet<>();

}
