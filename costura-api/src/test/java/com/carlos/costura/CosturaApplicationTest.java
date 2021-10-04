package com.carlos.costura;

import com.carlos.costura.domain.model.Post;
import com.carlos.costura.domain.model.pk.SaleItemPK;
import com.carlos.costura.domain.repository.SaleItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CosturaApplicationTest {

    @Autowired
    private SaleItemRepository saleItemRepository;

    @Test
    void main() {
    }
}