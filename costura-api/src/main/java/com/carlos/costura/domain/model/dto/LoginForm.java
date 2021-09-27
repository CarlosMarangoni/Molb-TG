package com.carlos.costura.domain.model.dto;

import com.carlos.costura.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {

    @NotEmpty
    private String username;

    @NotEmpty
    private String name;

    @Email
    @NotNull
    private String email;

    @NotEmpty
    private String password;

    public static LoginForm toForm(@Valid User user){
        return new LoginForm(
                user.getUser(),
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );
    }
}
