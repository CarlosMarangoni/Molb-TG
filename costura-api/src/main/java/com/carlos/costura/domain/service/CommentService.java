package com.carlos.costura.domain.service;

import com.carlos.costura.domain.model.Comment;
import com.carlos.costura.domain.model.Post;
import com.carlos.costura.domain.model.dto.CommentForm;
import com.carlos.costura.domain.repository.CommentRepository;
import com.carlos.costura.domain.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    public Comment save(CommentForm commentForm, Long postId) {
        Comment commentModel = Comment.toModel(commentForm);
        Post postOfTheComment = postRepository.findById(postId).get();
        postOfTheComment.addComment();
        commentModel.setPost(postOfTheComment);

        return commentRepository.save(commentModel);

    }
}
