package com.carlos.costura.domain.repository;


import com.carlos.costura.domain.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {

    @Query("SELECT s FROM Sale s WHERE :userId = s.user.id " +
            "ORDER BY s.date DESC")
    List<Sale> findAllByUser(@Param("userId") Long userId);

}
