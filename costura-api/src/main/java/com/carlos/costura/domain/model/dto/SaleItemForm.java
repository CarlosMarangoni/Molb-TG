package com.carlos.costura.domain.model.dto;

import com.carlos.costura.domain.model.SaleItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleItemForm {

    private String description;

    public static SaleItemForm toForm(SaleItem item){
        return new SaleItemForm(
                item.getDescription());
    }
}
