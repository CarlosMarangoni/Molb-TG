package com.carlos.costura.domain.model.dto;

import com.carlos.costura.domain.model.Sale;
import com.carlos.costura.domain.model.enumeration.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleOutput {

    private Long id;

    private PostItemForm items;

    private BigDecimal total;

    private PaymentMethod paymentMethod;

    private OffsetDateTime data;

    public static SaleOutput toOutput(Sale sale){
        return new SaleOutput(
            sale.getId(),
            PostItemForm.toForm(sale.getItem()),
            sale.getTotal(),
            sale.getPaymentMethod(),
            sale.getDate()
        );
    }
}

