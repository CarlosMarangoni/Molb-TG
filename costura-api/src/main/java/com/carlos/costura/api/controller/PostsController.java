package com.carlos.costura.api.controller;

import com.carlos.costura.domain.model.Post;
import com.carlos.costura.domain.model.dto.PostForm;
import com.carlos.costura.domain.repository.PostRepository;
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

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(){
        List<Post> posts = postRepository.findAll();
        return ResponseEntity.ok(posts);
    }

    @PostMapping
    public ResponseEntity<Post> addPost(@RequestBody PostForm postForm, UriComponentsBuilder uriComponentsBuilder){
        Post savedPost = postService.save(postForm);
        UriComponents uriComponents = uriComponentsBuilder.path("/posts/{id}").buildAndExpand(savedPost.getId());
        var location = uriComponents.toUri();
        return ResponseEntity.created(location).body(savedPost);
    }


}
