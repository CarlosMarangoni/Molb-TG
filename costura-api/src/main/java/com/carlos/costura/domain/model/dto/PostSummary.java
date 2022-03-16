package com.carlos.costura.domain.model.dto;

import com.carlos.costura.domain.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostSummary {

    private String postImage;

    private String user;

    private String description;

    private OffsetDateTime createdAt;

    private Integer commentAmount;

    private String category;

    public static PostSummary toSummary(Post post){
        return new PostSummary(
                post.getPostImage(),
                post.getUser().getUser(),
                post.getDescription(),
                post.getCreatedAt(),
                post.getCommentAmount(),
                post.getCategory().getName()
        );
    }
}

