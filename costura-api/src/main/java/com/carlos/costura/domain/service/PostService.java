package com.carlos.costura.domain.service;

import com.carlos.costura.domain.model.Post;
import com.carlos.costura.domain.model.SaleItem;
import com.carlos.costura.domain.model.dto.PostForm;
import com.carlos.costura.domain.model.dto.PostOutput;
import com.carlos.costura.domain.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post save(PostForm postForm) {
        AtomicInteger atomicSum = new AtomicInteger(0);
        Post postModel = Post.toModel(postForm);
        postModel.getItems().forEach(c ->{
            c.getSaleItemPK().setPost(postModel);
            c.getSaleItemPK().setItem(atomicSum.incrementAndGet());
        });

        return postRepository.save(postModel);
    }
}
