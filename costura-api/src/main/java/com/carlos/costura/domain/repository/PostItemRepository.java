package com.carlos.costura.domain.repository;


import com.carlos.costura.domain.model.PostItem;
import com.carlos.costura.domain.model.pk.PostItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostItemRepository extends JpaRepository<PostItem, PostItemPK> {

    boolean existsByPostItemPK_Post_IdAndPostItemPK_Item(Long postId,Integer item);

    boolean existsByPostItemPK(PostItemPK postItemPK);
}
