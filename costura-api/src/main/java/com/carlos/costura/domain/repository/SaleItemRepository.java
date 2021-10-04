package com.carlos.costura.domain.repository;


import com.carlos.costura.domain.model.SaleItem;
import com.carlos.costura.domain.model.pk.SaleItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem, SaleItemPK> {
}
