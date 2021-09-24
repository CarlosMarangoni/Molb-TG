package com.carlos.costura.domain.service;

import com.carlos.costura.domain.model.*;
import com.carlos.costura.domain.model.dto.CommentForm;
import com.carlos.costura.domain.model.dto.PostForm;
import com.carlos.costura.domain.repository.CommentRepository;
import com.carlos.costura.domain.repository.LikeRepository;
import com.carlos.costura.domain.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
public class  PostService {

    private PostRepository postRepository;

    private CommentRepository commentRepository;

    private LikeRepository likeRepository;

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
        Like like = new Like();
        Post likedPost = postRepository.findById(postId).get();
        like.getLikesPK().setPost(likedPost);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        like.getLikesPK().setUser(user);
        likedPost.plusOneLike();
        likeRepository.save(like);

    }

    public Comment addComment(CommentForm commentForm, Long postId) {
        Comment commentModel = Comment.toModel(commentForm);
        Post commentedPost = postRepository.findById(postId).get();
        commentModel.setPost(commentedPost);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        commentModel.setUser(user);
        commentedPost.plusOneComment();

        return commentRepository.save(commentModel);
    }

    public Purchase buy(Long postId) {
        Post postToBuy = postRepository.findById(postId).get();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return null;
    }
}
