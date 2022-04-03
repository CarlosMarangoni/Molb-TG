package com.carlos.costura.domain.repository;


import com.carlos.costura.domain.model.Post;
import com.carlos.costura.domain.model.SaleItem;
import com.carlos.costura.domain.model.User;
import com.carlos.costura.domain.model.pk.SaleItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem, SaleItemPK> {

    boolean existsBySaleItemPK_PostAndAndSaleItemPK_ItemAndSaleItemPK_Purchase_Buyer(Post post, Integer item, User buyer);

    boolean existsBySaleItemPK_Post_IdAndSaleItemPK_Item(Long postId,Integer item);
}
