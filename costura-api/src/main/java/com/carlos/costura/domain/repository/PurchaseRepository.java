package com.carlos.costura.domain.repository;


import com.carlos.costura.domain.model.Comment;
import com.carlos.costura.domain.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Long> {
}
