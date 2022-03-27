package com.carlos.costura.domain.repository;


import com.carlos.costura.domain.model.Category;
import com.carlos.costura.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Role,Long> {
}
