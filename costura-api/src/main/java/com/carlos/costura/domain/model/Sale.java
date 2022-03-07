package com.carlos.costura.domain.model;

import com.carlos.costura.domain.model.enumeration.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private User user;

    @ManyToOne
    private PostItem item;

    private BigDecimal total;

    @CreationTimestamp
    private OffsetDateTime date;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    public Sale(User user,PostItem item,BigDecimal total,PaymentMethod paymentMethod){
        this.user = user;
        this.item = item;
        this.total = total;
        this.paymentMethod = paymentMethod;
    }

}
