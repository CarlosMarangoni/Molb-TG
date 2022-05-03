package com.carlos.costura.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleItemForm {

    private Long postId;

    private Integer item;

    private String description;

    private BigDecimal price;
}
