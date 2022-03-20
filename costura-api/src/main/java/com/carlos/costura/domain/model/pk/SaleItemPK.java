package com.carlos.costura.domain.model.pk;

import com.carlos.costura.domain.model.Post;
import com.carlos.costura.domain.model.Purchase;
import com.carlos.costura.domain.model.dto.CartItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SaleItemPK implements Serializable {

    @ManyToOne
    @JsonIgnore
    private Purchase purchase;

    private Integer item;

    @ManyToOne
    private Post post;

    public SaleItemPK(Post post, Integer item) {
        this.post = post;
        this.item = item;
    }

}
