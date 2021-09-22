package com.carlos.costura.domain.service;

import com.carlos.costura.domain.model.Comment;
import com.carlos.costura.domain.model.Post;
import com.carlos.costura.domain.model.User;
import com.carlos.costura.domain.model.dto.CommentForm;
import com.carlos.costura.domain.repository.CommentRepository;
import com.carlos.costura.domain.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class CommentService {

    private CommentRepository commentRepository;

    private PostRepository postRepository;

    public Comment save(CommentForm commentForm, Long postId) {
        Comment commentModel = Comment.toModel(commentForm);
        Post postOfTheComment = postRepository.findById(postId).get();
        postOfTheComment.addComment();
        commentModel.setPost(postOfTheComment);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        commentModel.setUser(user);

        return commentRepository.save(commentModel);

    }
}
