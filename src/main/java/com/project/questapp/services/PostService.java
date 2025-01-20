package com.project.questapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.questapp.entities.Post;
import com.project.questapp.entities.USer;
import com.project.questapp.repository.PostRepository;
import com.project.questapp.requests.PostCreateRequest;
import com.project.questapp.requests.PostUpdateRequest;

import jakarta.persistence.PostUpdate;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserService userService;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<Post> getAllPosts(Optional<Long> userId) {
        if (userId.isPresent()) {
            return postRepository.findByUserId(userId.get());
        }
        return postRepository.findAll();
    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostCreateRequest) {
        USer user = userService.getOneUserById(newPostCreateRequest.getUserId());
        if (user == null) {
            return null;
        }
        Post toSave = new Post();
        toSave.setId(newPostCreateRequest.getId());
        toSave.setTitle(newPostCreateRequest.getTitle());
        toSave.setText(newPostCreateRequest.getText());
        toSave.setUser(user);

        return postRepository.save(toSave);
    }
    

    public Post updatePostById(Long postId, PostUpdateRequest postUpdate) {

        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            Post toUpdatePost = post.get();
            toUpdatePost.setText(postUpdate.getText());
            toUpdatePost.setTitle(postUpdate.getTitle());
            postRepository.save(toUpdatePost);
            return toUpdatePost;
        }

        return null;
    }

    public void deletePostById(Long postId) {
        postRepository.deleteById(postId);
    }

}
