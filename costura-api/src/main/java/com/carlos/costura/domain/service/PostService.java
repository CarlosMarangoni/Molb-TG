package com.carlos.costura.domain.service;

import com.carlos.costura.domain.exception.AuthorizationException;
import com.carlos.costura.domain.exception.ConflictException;
import com.carlos.costura.domain.exception.PageNotFoundException;
import com.carlos.costura.domain.model.*;
import com.carlos.costura.domain.model.dto.CommentForm;
import com.carlos.costura.domain.model.dto.PostForm;
import com.carlos.costura.domain.model.dto.PostItemForm;
import com.carlos.costura.domain.model.pk.PostItemPK;
import com.carlos.costura.domain.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
public class  PostService {

    private PostRepository postRepository;

    private CommentRepository commentRepository;

    private UserRepository userRepository;

    private PostItemRepository postItemRepository;

    private CategoryRepository categoryRepository;

    private S3Service s3Service;

    private EmailService emailService;

    public Post save(PostForm postForm,MultipartFile imageFile,List<MultipartFile> moldes) throws MessagingException, IOException {
        AtomicInteger atomicSum = new AtomicInteger(0);
        List<String> fileUrls = new ArrayList<>();
        if(User.isAuthenticated()){
            User user = userRepository.findById(postForm.getUserId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
            Category category = categoryRepository.findById(postForm.getCategoryId()).orElseThrow(() -> new NotFoundException("Categoria não encontrada"));
            Post postModel = Post.toModel(postForm);

            for (MultipartFile file : moldes) {
                String url = uploadMoldeFile(file);
                fileUrls.add(url);
            }

            if(imageFile != null){
                postModel.setPostImage(uploadPostPicture(imageFile).toString());
            }else{
                postModel.setPostImage("");
            }
            postModel.setAverageStars(0.0);
            postModel.setCategory(category);
            postModel.setUser(user);
            AtomicInteger i = new AtomicInteger(0   );
            postModel.getItems().forEach(c ->{
                c.setMoldeUrl(fileUrls.get(i.get()));
                c.getPostItemPK().setPost(postModel);
                c.getPostItemPK().setItem(atomicSum.incrementAndGet());
                i.getAndIncrement();
            });

            emailService.sendEmail(postModel);
            return postRepository.save(postModel);
        }else {
            throw new AuthorizationException("Acesso negado.");
        }

    }

    public Post addComment(CommentForm commentForm, Long postId) {
        User user = User.isAuthenticatedReturnUser();
        Comment commentModel = Comment.toModel(commentForm);
        Post commentedPost = postRepository.findById(postId).get();
        commentModel.setPost(commentedPost);
        commentModel.setUser(user);
        commentedPost.plusOneComment();

        Boolean hasCommented = commentedPost.getComments().stream().anyMatch(comment -> {
            return comment.getUser().getId().equals(commentModel.getUser().getId());
        });

        if(!hasCommented && !commentedPost.getUser().getId().equals(commentModel.getUser().getId())){ //User não comentou e não é o dono
            if(commentedPost.getComments().size() == 0){
                commentedPost.setAverageStars(commentModel.getStars().doubleValue());
            }else{
                commentedPost.setAverageStars((commentedPost.getAverageStars() + commentForm.getStars())/2);
            }

        }else{
            throw new ConflictException("Não é possível adicionar mais comentários.");
        }
        Comment comment = commentRepository.save(commentModel);
        commentedPost.getComments().add(comment);
        return commentedPost;
    }

    public URI uploadPostPicture(MultipartFile multipartFile)
    {
        return s3Service.uploadFile(multipartFile);
    }

    public String uploadMoldeFile(MultipartFile multipartFile)
    {
        return s3Service.uploadFile(multipartFile).toString();
    }

    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }

    public Post addItem(PostItemForm postItemForm, Long postId) {
        PostItem postItem = PostItem.toModel(postItemForm);
        User loggedUser = User.isAuthenticatedReturnUser();
        Post post = postRepository.findById(postId).orElseThrow(() -> new PageNotFoundException("Post não encontrado."));
        if(loggedUser.getId().equals(post.getUser().getId())){
           List<PostItem> items = post.getItems();
           postItem.getPostItemPK().setPost(post);
           postItem.getPostItemPK().setItem(items.size()+1);
           items.add(postItem);
           post.setItems(items);
        }else{
            throw new AuthorizationException("Acesso negado.");
        }

        return postRepository.save(post);
    }

    public void deleteItem(Long postId, Integer itemId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PageNotFoundException("Post não encontrado."));
        User loggedUser = User.isAuthenticatedReturnUser();
        List<PostItem> items = post.getItems();
        if(loggedUser.getId().equals(post.getUser().getId())) {
            postItemRepository.deleteById(PostItemPK.builder()
                            .item(itemId)
                            .post(post)
                    .build());
        }else{
            throw new AuthorizationException("Acesso negado.");
        }
    }
}
