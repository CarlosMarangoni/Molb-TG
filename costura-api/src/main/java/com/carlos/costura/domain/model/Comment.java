package com.carlos.costura.domain.model;

import com.carlos.costura.domain.model.dto.CommentForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Integer stars;

    @ManyToOne
    private User user;

    @ManyToOne
    @JsonIgnore
    private Post post;

    public Comment(String description, Integer stars) {
        this.description = description;
        this.stars = stars;
    }

    public static Comment toModel(CommentForm commentForm) {
        return new Comment(commentForm.getDescription(),
                commentForm.getStars());
    }
}
