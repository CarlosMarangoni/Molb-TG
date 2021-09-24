package com.carlos.costura.domain.model.dto;

import com.carlos.costura.domain.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostOutput {

    private String postImage;

    private String user;

    private String description;

    private BigDecimal price;

    private OffsetDateTime createdAt;

    private List<LikeOutput> likes;

    private List<CommentOutput> comments;

    private List<SaleItemForm> items;

    public static PostOutput toOutput(Post post){
        return new PostOutput(
                post.getPostImage(),
                post.getUser().getUser(),
                post.getDescription(),
                post.getPrice(),
                post.getCreatedAt(),
                post.getLikes().stream().map(LikeOutput::toOutput).collect(Collectors.toList()),
                post.getComments().stream().map(CommentOutput::toOutput).collect(Collectors.toList()),
                post.getItems().stream().map(SaleItemForm::toForm).collect(Collectors.toList()));
    }

}
