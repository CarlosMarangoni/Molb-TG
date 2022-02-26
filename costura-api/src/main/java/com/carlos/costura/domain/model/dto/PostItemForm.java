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
public class PostItemForm {

    private String moldeUrl;

    @NotEmpty
    private String description;

    @NotEmpty
    private BigDecimal price;

    public static PostItemForm toForm(PostItem item){
        return new PostItemForm(
                item.getMoldeUrl(),
                item.getDescription(),
                item.getPrice());
    }
}
