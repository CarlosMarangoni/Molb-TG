package com.carlos.costura;

import com.carlos.costura.domain.repository.PostItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CosturaApplicationTest {

    @Autowired
    private PostItemRepository postItemRepository;

    @Test
    void main() {
    }
}