package com.carlos.costura.domain.model.dto;

import com.carlos.costura.domain.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostOutput {

    private Long id;

    private String postImage;

    private Double averageStars;

    private Long userId;

    private String profileImage;

    private String user;

    private String title;

    private String description;

    private OffsetDateTime createdAt;

    private String category;

    private List<CommentOutput> comments;

    private List<PostItemOutput> items;

    public static PostOutput toOutput(Post post){
        return new PostOutput(
                post.getId(),
                post.getPostImage(),
                post.getAverageStars(),
                post.getUser().getId(),
                post.getUser().getProfileImage(),
                post.getUser().getUser(),
                post.getTitle(),
                post.getDescription(),
                post.getCreatedAt(),
                post.getCategory().getName(),
                post.getComments().stream().map(CommentOutput::toOutput).collect(Collectors.toList()),
                post.getItems().stream().map(PostItemOutput::toOutput).collect(Collectors.toList()));
    }

}
