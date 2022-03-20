package com.carlos.costura.domain.repository;


import com.carlos.costura.domain.model.Purchase;
import com.carlos.costura.domain.model.SaleItem;
import com.carlos.costura.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Long> {

    boolean existsByBuyerAndItems(User buyer, SaleItem item);

}
