package com.carlos.costura.domain.service;

import com.carlos.costura.domain.exception.AuthorizationException;
import com.carlos.costura.domain.exception.PageNotFoundException;
import com.carlos.costura.domain.model.*;
import com.carlos.costura.domain.model.dto.CommentForm;
import com.carlos.costura.domain.model.dto.PostForm;
import com.carlos.costura.domain.model.dto.SaleItemForm;
import com.carlos.costura.domain.model.enumeration.Category;
import com.carlos.costura.domain.model.pk.SaleItemPK;
import com.carlos.costura.domain.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
public class  PostService {

    private PostRepository postRepository;

    private CommentRepository commentRepository;

    private LikeRepository likeRepository;

    private UserRepository userRepository;

    private SaleItemRepository saleItemRepository;

    private S3Service s3Service;

    public Post save(PostForm postForm,MultipartFile imageFile) {
        AtomicInteger atomicSum = new AtomicInteger(0);
        if(User.isAuthenticated()){
            User user = userRepository.findById(postForm.getUserId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));


            Post postModel = Post.toModel(postForm);
            if(imageFile != null){
                postModel.setPostImage(uploadPostPicture(imageFile).toString());
            }else{
                postModel.setPostImage("");
            }
            postModel.setAverageStars(0.0);
            postModel.setUser(user);
            postModel.getItems().forEach(c ->{
                c.getSaleItemPK().setPost(postModel);
                c.getSaleItemPK().setItem(atomicSum.incrementAndGet());
            });

            return postRepository.save(postModel);
        }else {
            throw new AuthorizationException("Acesso negado.");
        }

    }

    public void addLike(Long postId) {
        User user = User.isAuthenticatedReturnUser();
        Like like = new Like();
        Post likedPost = postRepository.findById(postId).get();
        like.getLikesPK().setPost(likedPost);
        like.getLikesPK().setUser(user);
        likedPost.plusOneLike();
        likeRepository.save(like);

    }

    public Comment addComment(CommentForm commentForm, Long postId) {
        User user = User.isAuthenticatedReturnUser();
        Comment commentModel = Comment.toModel(commentForm);
        Post commentedPost = postRepository.findById(postId).get();
        commentModel.setPost(commentedPost);
        commentModel.setUser(user);
        commentedPost.plusOneComment();

        Boolean hasCommented = commentedPost.getComments().stream().anyMatch(comment -> {
            return comment.getUser().getId().equals(commentModel.getUser().getId());
        });

        if(!hasCommented && !commentedPost.getUser().getId().equals(commentModel.getUser().getId())){
            if(commentedPost.getComments().size() == 0){
                commentedPost.setAverageStars(commentModel.getStars().doubleValue());
            }else{
                commentedPost.setAverageStars((commentedPost.getAverageStars() + commentForm.getStars())/2);
            }

        }
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
        User loggedUser = User.isAuthenticatedReturnUser();
        Post post = postRepository.findById(postId).orElseThrow(() -> new PageNotFoundException("Post não encontrado."));
        if(loggedUser.getId().equals(post.getUser().getId())){
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

    public void deleteItem(Long postId, Integer itemId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PageNotFoundException("Post não encontrado."));
        User loggedUser = User.isAuthenticatedReturnUser();
        List<SaleItem> items = post.getItems();
        if(loggedUser.getId().equals(post.getUser().getId())) {
            saleItemRepository.deleteById(SaleItemPK.builder()
                            .item(itemId)
                            .post(post)
                    .build());
        }else{
            throw new AuthorizationException("Acesso negado.");
        }
    }
}
