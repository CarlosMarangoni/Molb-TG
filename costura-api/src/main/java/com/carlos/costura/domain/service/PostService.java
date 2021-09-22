package com.carlos.costura.domain.service;

import com.carlos.costura.domain.model.Post;
import com.carlos.costura.domain.model.Purchase;
import com.carlos.costura.domain.model.User;
import com.carlos.costura.domain.model.dto.PostForm;
import com.carlos.costura.domain.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
public class  PostService {

    private PostRepository postRepository;

    public Post save(PostForm postForm) {
        AtomicInteger atomicSum = new AtomicInteger(0);
        Post postModel = Post.toModel(postForm);
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        postModel.setUser(user);
        postModel.getItems().forEach(c ->{
            c.getSaleItemPK().setPost(postModel);
            c.getSaleItemPK().setItem(atomicSum.incrementAndGet());
        });

        return postRepository.save(postModel);
    }

    public void addLike(Long postId) {
        Post likedPost = postRepository.findById(postId).get();
        likedPost.addLike();
    }

    public Purchase buy(Long postId){


        return null;
    }
}
