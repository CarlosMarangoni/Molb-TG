package com.carlos.costura.domain.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Collection<? extends GrantedAuthority> authorities;
    private UserSummary user;

    public JwtResponse(String accessToken, Collection<? extends GrantedAuthority> authorities,UserSummary user) {
        this.token = accessToken;
        this.authorities = authorities;
        this.user = user;
    }
}
