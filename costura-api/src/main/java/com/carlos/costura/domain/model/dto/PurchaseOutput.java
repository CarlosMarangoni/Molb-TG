package com.carlos.costura.domain.model.dto;

import com.carlos.costura.domain.model.Purchase;
import com.carlos.costura.domain.model.enumeration.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOutput {

    private Long id;

    private List<PostItemForm> items;

    private BigDecimal total;

    private PaymentMethod paymentMethod;

    private OffsetDateTime data;

    public static PurchaseOutput toOutput(Purchase purchase){
        return new PurchaseOutput(
            purchase.getId(),
            purchase.getItems().stream().map(i -> PostItemForm.toForm(i)).collect(Collectors.toList()),
            purchase.getTotal(),
            purchase.getPaymentMethod(),
            purchase.getDate()
        );
    }
}

