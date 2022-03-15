package com.carlos.costura.domain.model;

import com.carlos.costura.domain.exception.AuthorizationException;
import com.carlos.costura.domain.model.dto.RegistrationForm;
import com.carlos.costura.domain.model.pk.FollowersPK;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Builder
public class Followers {

    @EmbeddedId
    private FollowersPK followersPK;

}
