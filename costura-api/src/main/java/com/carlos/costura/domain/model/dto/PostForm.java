package com.carlos.costura.domain.model.dto;

import com.carlos.costura.domain.model.Comment;
import com.carlos.costura.domain.model.Post;
import com.carlos.costura.domain.model.SaleItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PostForm {

    @NotEmpty
    private String description;

    @NotNull
    private BigDecimal price;

    @NotEmpty
    private List<SaleItemForm> items = new ArrayList<>();

    public PostForm(String description, BigDecimal price, List<SaleItemForm> items) {
        this.description = description;
        this.price = price;
        this.items = items;
    }

    public static PostForm toForm(Post post){
        return new PostForm(
                post.getDescription(),
                post.getPrice(),
                post.getItems().stream().map(SaleItemForm::toForm).collect(Collectors.toList()));
    }

}
