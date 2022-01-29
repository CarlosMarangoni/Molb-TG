package com.carlos.costura.domain.model.dto;

import com.carlos.costura.domain.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentForm {

    private String description;

    @Max(5)
    private Integer stars;

    public static CommentForm toForm(Comment comment){
        return new CommentForm(comment.getDescription(), comment.getStars());
    }

}
