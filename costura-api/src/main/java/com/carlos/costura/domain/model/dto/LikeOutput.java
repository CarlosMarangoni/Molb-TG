package com.carlos.costura.domain.model.dto;

import com.carlos.costura.domain.model.Like;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LikeOutput {

    private String user;

    public static LikeOutput toOutput(Like like){
        return new LikeOutput(like.getLikesPK().getUser().getUser());
    }
}
