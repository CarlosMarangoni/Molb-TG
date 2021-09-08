package com.carlos.costura.domain.model.dto;

import com.carlos.costura.domain.model.Post;
import com.carlos.costura.domain.model.SaleItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostForm {

    private String postImage;

    private String description;

    private List<SaleItemForm> items;

    public static PostForm toForm(Post post){
        return new PostForm(
                post.getPostImage(),
                post.getDescription(),
                post.getItems().stream().map(SaleItemForm::toForm).collect(Collectors.toList()));
    }

}
