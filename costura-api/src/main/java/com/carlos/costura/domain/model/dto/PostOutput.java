package com.carlos.costura.domain.model.dto;

import com.carlos.costura.domain.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostOutput {

    private String postImage;

    private String description;

    private BigDecimal price;

    private List<SaleItemForm> items;

    public static PostOutput toOutput(Post post){
        return new PostOutput(
                post.getPostImage(),
                post.getDescription(),
                post.getPrice(),
                post.getItems().stream().map(SaleItemForm::toForm).collect(Collectors.toList()));
    }

}
