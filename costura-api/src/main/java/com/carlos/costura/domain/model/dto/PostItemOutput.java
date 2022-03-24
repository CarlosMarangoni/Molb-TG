package com.carlos.costura.domain.model.dto;

import com.carlos.costura.domain.model.PostItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostItemOutput {

    private Long postId;

    private Integer item;

    private String moldeUrl;

    private String description;

    private BigDecimal price;

    public static PostItemOutput toOutput(PostItem item){
        return new PostItemOutput(
                item.getPostItemPK().getPost().getId(),
                item.getPostItemPK().getItem(),
                item.getMoldeUrl(),
                item.getDescription(),
                item.getPrice());
    }
}
