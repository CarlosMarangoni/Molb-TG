package com.carlos.costura.domain.repository;

import com.carlos.costura.domain.model.Post;
import com.carlos.costura.domain.model.User;
import com.carlos.costura.domain.model.dto.PostOutput;
import com.carlos.costura.domain.model.enumeration.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findAllByUser(User user);

    List<Post> findAllByCategory(Category category);

//    @Query("SELECT p FROM Post p WHERE :id MEMBER OF p.user.followers")
//    List<Post> findAllByUserFollowed(@Param("id") User user);
}
