package com.carlos.costura.domain.repository;


import com.carlos.costura.domain.model.Comment;
import com.carlos.costura.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
