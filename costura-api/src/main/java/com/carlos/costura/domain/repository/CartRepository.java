package com.carlos.costura.domain.repository;


import com.carlos.costura.domain.model.Purchase;
import com.carlos.costura.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Purchase,Long> {

    @Query("SELECT p FROM Purchase p WHERE :userId = p.user.id " +
            "ORDER BY p.date DESC")
    List<Purchase> findAllByUser(@Param("userId") Long userId);

}
