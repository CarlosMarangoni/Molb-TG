package com.carlos.costura.domain.repository;


import com.carlos.costura.domain.model.PostItem;
import com.carlos.costura.domain.model.Purchase;
import com.carlos.costura.domain.model.SaleItem;
import com.carlos.costura.domain.model.User;
import com.carlos.costura.domain.model.dto.SaleItemOutput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Purchase,Long> {

    @Query("SELECT p FROM Purchase p WHERE :userId = p.buyer.id " +
            "ORDER BY p.date DESC")
    List<Purchase> findAllByUser(@Param("userId") Long userId);

    @Query("SELECT new com.carlos.costura.domain.model.dto.SaleItemOutput(" +
            "saleItem.saleItemPK.post.id,saleItem.saleItemPK.item,postItem.description,postItem.price,purchase.date) " +
            "from SaleItem saleItem" +
            " JOIN PostItem postItem ON postItem.postItemPK.post.id = saleItem.saleItemPK.post.id " +
            "AND postItem.postItemPK.item = saleItem.saleItemPK.item " +
            "JOIN Purchase purchase ON saleItem.saleItemPK.purchase.id = purchase.id")
    List<SaleItemOutput> findAllSalesByUser(@Param("userId") Long userId);

}
