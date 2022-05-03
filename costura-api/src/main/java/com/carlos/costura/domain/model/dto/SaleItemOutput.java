package com.carlos.costura.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
public class SaleItemOutput {

    private Long postId;

    private Integer item;

    private String description;

    private BigDecimal price;

    private OffsetDateTime data;

    private String buyer;

}
