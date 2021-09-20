package com.carlos.costura.domain.model.dto;

import com.carlos.costura.domain.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentOutput {

    private String description;

    private Integer stars;

    private Long postId;

    public static CommentOutput toOutput(Comment comment){
        return new CommentOutput(comment.getDescription(), comment.getStars(),comment.getPost().getId());
    }

}
