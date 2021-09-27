package com.carlos.costura.domain.model.dto;

import com.carlos.costura.domain.model.Comment;
import com.carlos.costura.domain.model.Post;
import com.carlos.costura.domain.model.SaleItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostForm {

    private String postImage;

    @NotEmpty
    private String description;

    @NotNull
    private BigDecimal price;

    private List<SaleItemForm> items;

    public static PostForm toForm(Post post){
        return new PostForm(
                post.getPostImage(),
                post.getDescription(),
                post.getPrice(),
                post.getItems().stream().map(SaleItemForm::toForm).collect(Collectors.toList()));
    }

}
