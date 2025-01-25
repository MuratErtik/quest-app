package com.project.questapp.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.questapp.entities.Like;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.USer;
import com.project.questapp.repository.PostRepository;
import com.project.questapp.requests.PostCreateRequest;
import com.project.questapp.requests.PostUpdateRequest;
import com.project.questapp.responses.LikeResponse;
import com.project.questapp.responses.PostResponse;

import jakarta.persistence.PostUpdate;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserService userService;
    private LikeService likeService;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public void setLikeService(LikeService likeService){
        this.likeService=likeService;
        //
    }

    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> list;
        if (userId.isPresent()) {
            list= postRepository.findByUserId(userId.get());
        }
        else{
            list= postRepository.findAll();

        }
        return list.stream().map(p -> {
            
            List<LikeResponse> likes=likeService.getAllLikesWithParam(Optional.ofNullable(null), Optional.of(p.getId()));
            return new PostResponse(p,likes);}).collect(Collectors.toList());
            //stream listenin içindeki her bir öğeyi sırasıyla işlemeye olanak tanır.
            //map metodu, akıştaki her öğeyi alır ve alinan ogeden bir PostResp  nesnesi olusturur.
            // collect sayesindde PostResponse nesnelerinden oluşan bir liste var.
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
        toSave.setCreateDate((java.sql.Date) new Date());

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
