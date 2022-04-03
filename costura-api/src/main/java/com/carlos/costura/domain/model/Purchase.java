package com.carlos.costura.domain.model;

import com.carlos.costura.domain.model.dto.CartForm;
import com.carlos.costura.domain.model.enumeration.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "saleItemPK.purchase",cascade = CascadeType.ALL)
    private List<SaleItem> items = new ArrayList<>();

    @CreationTimestamp
    private OffsetDateTime date;

    @ManyToOne
    private User buyer;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private BigDecimal total;


    public Purchase(List<SaleItem> items) {
        this.items = items;
    }

    public static Purchase toModel(CartForm cartForm) {
        return new Purchase(
                cartForm.getItems().stream().map(SaleItem::toModel).collect(Collectors.toList())
        );
    }
}
