package com.carlos.costura.domain.service;

import com.carlos.costura.domain.exception.AuthorizationException;
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
        User user = User.isAuthenticated();
        Post postModel = Post.toModel(postForm);
        postModel.setUser(user);
        postModel.getItems().forEach(c ->{
            c.getSaleItemPK().setPost(postModel);
            c.getSaleItemPK().setItem(atomicSum.incrementAndGet());
        });

        return postRepository.save(postModel);
    }

    public void addLike(Long postId) {
        User user = User.isAuthenticated();
        Like like = new Like();
        Post likedPost = postRepository.findById(postId).get();
        like.getLikesPK().setPost(likedPost);
        like.getLikesPK().setUser(user);
        likedPost.plusOneLike();
        likeRepository.save(like);

    }

    public Comment addComment(CommentForm commentForm, Long postId) {
        User user = User.isAuthenticated();
        Comment commentModel = Comment.toModel(commentForm);
        Post commentedPost = postRepository.findById(postId).get();
        commentModel.setPost(commentedPost);
        commentModel.setUser(user);
        commentedPost.plusOneComment();

        return commentRepository.save(commentModel);
    }

    public Purchase buy(Long postId) {
        Post postToBuy = postRepository.findById(postId).get();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null){
            throw new AuthorizationException("Acesso negado.");
        }
        return null;
    }
}
