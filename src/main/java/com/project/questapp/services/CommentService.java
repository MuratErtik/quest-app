package com.project.questapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.questapp.entities.Comment;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.USer;
import com.project.questapp.repository.CommentRepository;
import com.project.questapp.repository.PostRepository;
import com.project.questapp.repository.UserRepository;
import com.project.questapp.requests.CommentCreateRequest;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    
    public CommentService(CommentRepository commentRepository,UserRepository userRepository,PostRepository postRepository){

        this.commentRepository=commentRepository;
        this.userService=userService;
        this.postService=postService;
        
    }

    public List<Comment> getAllCommentsWithParams(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }
        else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        }
        else if (postId.isPresent()) {
            return commentRepository.findByPostid(postId.get());
        }
        else{
            return commentRepository.findAll();
        }        
    }

    public Comment getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createComment(CommentCreateRequest request) {
        //varolan bir post olmali ve varolan bir kullanici atmali!

        USer user = userService.getOneUserById(request.getUserId());
        Post post = postService.getOnePostById(request.getPostId());
        if (user!=null && post != null) {
            Comment commentToSave = new Comment();
            commentToSave.setId(request.getId());
            commentToSave.setUser(user);
            commentToSave.setPost(post);
            commentToSave.setText(request.getText());
            return commentRepository.save(commentToSave);
            
        }
        else{
            return null;

        }

    }




















}
