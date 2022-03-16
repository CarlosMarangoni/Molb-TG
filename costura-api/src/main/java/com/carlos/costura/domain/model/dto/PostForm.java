package com.carlos.costura.domain.model.dto;

import com.carlos.costura.domain.model.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PostForm {

    @NotEmpty
    private String title;

    @NotEmpty
    private String description;

    @NotNull
    private Long userId;

    @NotEmpty
    private String category;

    @NotEmpty
    private List<PostItemForm> items = new ArrayList<>();

    public PostForm(String title,String description,String category,Long userId,List<PostItemForm> items) {
        this.description = description;
        this.title = title;
        this.userId = userId;
        this.category = category;
        this.items = items;
    }

    public static PostForm toForm(Post post){
        return new PostForm(
                post.getTitle(),
                post.getDescription(),
                post.getCategory().getName(),
                post.getUser().getId(),
                post.getItems().stream().map(PostItemForm::toForm).collect(Collectors.toList()));
    }

}
