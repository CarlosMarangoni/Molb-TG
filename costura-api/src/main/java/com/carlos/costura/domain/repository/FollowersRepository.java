package com.carlos.costura.domain.repository;


import com.carlos.costura.domain.model.Followers;
import com.carlos.costura.domain.model.Purchase;
import com.carlos.costura.domain.model.User;
import com.carlos.costura.domain.model.pk.FollowersPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowersRepository extends JpaRepository<Followers, FollowersPK> {
}
