package com.carlos.costura.domain.model.dto;

import com.carlos.costura.domain.model.SaleItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleItemForm {


    @NotEmpty
    private String description;

    @NotEmpty
    private BigDecimal price;

    public static SaleItemForm toForm(SaleItem item){
        return new SaleItemForm(
                item.getDescription(),
                item.getPrice());
    }
}
