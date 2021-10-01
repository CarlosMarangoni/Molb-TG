package com.carlos.costura.domain.service;

import com.carlos.costura.domain.exception.AuthorizationException;
import com.carlos.costura.domain.exception.PageNotFoundException;
import com.carlos.costura.domain.model.*;
import com.carlos.costura.domain.model.dto.CommentForm;
import com.carlos.costura.domain.model.dto.PostForm;
import com.carlos.costura.domain.model.dto.SaleItemForm;
import com.carlos.costura.domain.repository.CommentRepository;
import com.carlos.costura.domain.repository.LikeRepository;
import com.carlos.costura.domain.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
public class  PostService {

    private PostRepository postRepository;

    private CommentRepository commentRepository;

    private LikeRepository likeRepository;

    private S3Service s3Service;

    public Post save(PostForm postForm,MultipartFile imageFile) {
        AtomicInteger atomicSum = new AtomicInteger(0);
        User user = User.isAuthenticated();
        Post postModel = Post.toModel(postForm);
        if(imageFile != null){
            postModel.setPostImage(uploadPostPicture(imageFile).toString());
        }else{
            postModel.setPostImage("");
        }
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

    public URI uploadPostPicture(MultipartFile multipartFile)
    {
        return s3Service.uploadFile(multipartFile);
    }

    public Purchase buy(Long postId) {
        Post postToBuy = postRepository.findById(postId).get();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null){
            throw new AuthorizationException("Acesso negado.");
        }
        return null;
    }

    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }

    public Post addItem(SaleItemForm saleItemForm, Long postId) {
        SaleItem saleItem = SaleItem.toModel(saleItemForm);
        User loggedUser = User.isAuthenticated();
        Post post = postRepository.findById(postId).orElseThrow(() -> new PageNotFoundException("Post não encontrado."));
        if(loggedUser.getId() == post.getUser().getId()){
           List<SaleItem> items = post.getItems();
           saleItem.getSaleItemPK().setPost(post);
            saleItem.getSaleItemPK().setItem(items.size()+1);
           items.add(saleItem);
           post.setItems(items);
        }else{
            throw new AuthorizationException("Acesso negado.");
        }

        return postRepository.save(post);
    }
}
