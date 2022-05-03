package com.carlos.costura.domain.model.dto;

import com.carlos.costura.domain.model.Purchase;
import com.carlos.costura.domain.model.enumeration.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOutput {

    private Long id;

    private List<SaleItemForm> items = new ArrayList<>();

    private BigDecimal total;

    private String paymentMethod;

    private OffsetDateTime data;
}

