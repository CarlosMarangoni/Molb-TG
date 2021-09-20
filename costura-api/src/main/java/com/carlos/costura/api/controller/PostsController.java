package com.carlos.costura.api.controller;

import com.carlos.costura.domain.model.Comment;
import com.carlos.costura.domain.model.Post;
import com.carlos.costura.domain.model.dto.CommentForm;
import com.carlos.costura.domain.model.dto.CommentOutput;
import com.carlos.costura.domain.model.dto.PostForm;
import com.carlos.costura.domain.model.dto.PostOutput;
import com.carlos.costura.domain.repository.CommentRepository;
import com.carlos.costura.domain.repository.PostRepository;
import com.carlos.costura.domain.service.CommentService;
import com.carlos.costura.domain.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(){
        List<Post> posts = postRepository.findAll();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id){
        Post post = postRepository.findById(id).get();
        return ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity<PostOutput> addPost(@RequestBody PostForm postForm, UriComponentsBuilder uriComponentsBuilder){
        Post savedPost = postService.save(postForm);
        UriComponents uriComponents = uriComponentsBuilder.path("/posts/{id}").buildAndExpand(savedPost.getId());
        var location = uriComponents.toUri();
        return ResponseEntity.created(location).body(PostOutput.toOutput(savedPost));
    }

    @PostMapping("/{postId}/comment")
    public ResponseEntity<CommentOutput> addComment(@PathVariable Long postId,
                                                    @RequestBody CommentForm commentForm, UriComponentsBuilder uriComponentsBuilder){
        Comment savedComment = commentService.save(commentForm,postId);
        UriComponents uriComponents = uriComponentsBuilder.path("/posts/{postId}/comment/{id}").buildAndExpand(savedComment.getPost().getId(),savedComment.getId());
        var location = uriComponents.toUri();
        return ResponseEntity.created(location).body(CommentOutput.toOutput(savedComment));
    }

//    @PostMapping("/{postId}/like")
//    public ResponseEntity<CommentOutput> addLike(@PathVariable Long postId, UriComponentsBuilder uriComponentsBuilder){
//
//        return null;
//    }






}
