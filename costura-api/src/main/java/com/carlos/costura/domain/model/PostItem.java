package com.carlos.costura.domain.model;

import com.carlos.costura.domain.model.dto.PostItemForm;
import com.carlos.costura.domain.model.pk.PostItemPK;
import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Builder
public class PostItem {

    @EmbeddedId
    private PostItemPK postItemPK = new PostItemPK();

    private String moldeUrl;

    private String description;

    @NotNull
    private BigDecimal price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostItem postItem = (PostItem) o;
        return postItemPK.equals(postItem.postItemPK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postItemPK);
    }

    public PostItem(String moldeUrl, String description, BigDecimal price) {
        this.moldeUrl = moldeUrl;
        this.description = description;
        this.price = price;
    }

    public static PostItem toModel(PostItemForm postItemForm) {
        return new PostItem(
                postItemForm.getMoldeUrl(),
                postItemForm.getDescription(),
                postItemForm.getPrice());
    }
}
