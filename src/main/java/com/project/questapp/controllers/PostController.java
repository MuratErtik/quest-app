package com.project.questapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.questapp.entities.Post;
import com.project.questapp.requests.PostCreateRequest;
import com.project.questapp.requests.PostUpdateRequest;
import com.project.questapp.services.PostService;

@RestController
@RequestMapping("/posts")

public class PostController {
    private PostService postService;

    PostController(PostService postService){
        this.postService=postService;
    }

    @GetMapping

    public List<Post> getAllPosts(@RequestParam Optional<Long> userId){
        //birden fazla kez kullanilablir sagindaki degiskene atar reqparam
        return postService.getAllPosts(userId);

    }

    @GetMapping("/{postId}")
    public Post getOnePost(@PathVariable Long postId){
        return postService.getOnePostById(postId);
    }


    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest newPostCreateRequest){
        return postService.createOnePost(newPostCreateRequest);
    }


    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequest postUpdateRequest){
        return postService.updatePostById(postId, postUpdateRequest);
    }


    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId){
        postService.deletePostById(postId);
    }




}
