package com.carlos.costura.domain.model.dto;

import com.carlos.costura.domain.model.PostItem;
import com.carlos.costura.domain.model.SaleItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleItemForm {

    private Long postId;

    private Integer item;

    public static SaleItemForm toForm(SaleItem item){
        return new SaleItemForm(
                item.getSaleItemPK().getPost().getId(),
                item.getSaleItemPK().getItem());
    }
}
