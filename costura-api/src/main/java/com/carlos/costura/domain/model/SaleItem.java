package com.carlos.costura.domain.model;

import com.carlos.costura.domain.model.dto.SaleItemForm;
import com.carlos.costura.domain.model.pk.SaleItemPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class SaleItem {

    @EmbeddedId
    private SaleItemPK saleItemPK = new SaleItemPK();

    private String moldeUrl;

    private String description;

    @NotNull
    private BigDecimal price;

    public SaleItem(String description,BigDecimal price) {
        this.description = description;
        this.price = price;
    }

    public static SaleItem toModel(SaleItemForm saleItemForm) {
        return new SaleItem(saleItemForm.getDescription(),
                saleItemForm.getPrice());
    }
}
