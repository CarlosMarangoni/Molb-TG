package com.carlos.costura.domain.model;

import com.carlos.costura.domain.model.dto.CartForm;
import com.carlos.costura.domain.model.dto.CartItem;
import com.carlos.costura.domain.model.dto.PostItemForm;
import com.carlos.costura.domain.model.pk.PostItemPK;
import com.carlos.costura.domain.model.pk.SaleItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
@Builder
public class SaleItem {

    @EmbeddedId
    private SaleItemPK saleItemPK = new SaleItemPK();

    public static SaleItem toModel(CartItem cartItem) {
        return new SaleItem(
                new SaleItemPK(Post.of(cartItem.getPostId()),cartItem.getItem()));
    }
}
