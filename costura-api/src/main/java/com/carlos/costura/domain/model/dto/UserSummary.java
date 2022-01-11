package com.carlos.costura.domain.model.dto;

import com.carlos.costura.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSummary {

    private Long id;

    private String user;

    private String profileImage;

    private String name;

    public static UserSummary toOutput(User user){
        return new UserSummary(
                user.getId(),
                user.getUser(),
                user.getProfileImage(),
                user.getName());
    }
}
