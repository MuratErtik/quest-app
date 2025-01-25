package com.project.questapp.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.questapp.entities.Comment;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.USer;
import com.project.questapp.repository.CommentRepository;
import com.project.questapp.repository.PostRepository;
import com.project.questapp.repository.UserRepository;
import com.project.questapp.requests.CommentCreateRequest;
import com.project.questapp.requests.CommentUpdateRequest;
import com.project.questapp.responses.CommentResponse;

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

    public List<CommentResponse> getAllCommentsWithParams(Optional<Long> userId, Optional<Long> postId) {
        List<Comment> commnets;
        if (userId.isPresent() && postId.isPresent()) {
            commnets =commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }
        else if (userId.isPresent()) {
            commnets =commentRepository.findByUserId(userId.get());
        }
        else if (postId.isPresent()) {
            commnets =commentRepository.findByPostId(postId.get());
        }
        else{
            commnets =commentRepository.findAll();
        }      
        return commnets.stream().map(comment -> new CommentResponse(comment)).collect(Collectors.toList());  
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
            commentToSave.setCreateDate( new Date(0));
            return commentRepository.save(commentToSave);
            
        }
        else{
            return null;

        }

    }

    public Comment updateCommentById(Long commentId, CommentUpdateRequest commentUpdateRequest) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            Comment commentToUpdate= comment.get();
            commentToUpdate.setText(commentUpdateRequest.getText());
            return commentRepository.save(commentToUpdate);
        }
        else{
            return null;
        }
    }

    public void deleteCommentById(Long commentId) {
        // TODO Auto-generated method stub
        commentRepository.deleteById(commentId);
    }




















}
