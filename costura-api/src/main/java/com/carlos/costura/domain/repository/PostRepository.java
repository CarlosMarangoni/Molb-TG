package com.carlos.costura.domain.repository;

import com.carlos.costura.domain.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
