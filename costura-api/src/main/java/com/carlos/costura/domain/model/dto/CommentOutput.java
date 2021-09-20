package com.carlos.costura.domain.model.dto;

import com.carlos.costura.domain.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentOutput {

    private String username;

    private String description;

    private Integer stars;

    public static CommentOutput toOutput(Comment comment){
        return new CommentOutput(comment.getUser().getUsername(),comment.getDescription(), comment.getStars());
    }

}
