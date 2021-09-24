package com.carlos.costura.domain.model.dto;

import com.carlos.costura.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowerOutput {

    private String user;

    private String name;

    public static FollowerOutput toOutput(User user){
        return new FollowerOutput(
                user.getUser(),
                user.getName()
        );
    }
}
