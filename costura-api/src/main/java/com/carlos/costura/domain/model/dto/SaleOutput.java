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
public class SaleOutput {

    private List<SaleItemOutput> items;

}

