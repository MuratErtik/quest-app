package com.project.questapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.questapp.entities.Comment;
import com.project.questapp.repository.CommentRepository;
import com.project.questapp.repository.PostRepository;
import com.project.questapp.repository.UserRepository;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    private UserRepository userRepository;

    private PostRepository postRepository;

    public CommentService(CommentRepository commentRepository,UserRepository userRepository,PostRepository postRepository){

        this.commentRepository=commentRepository;
        this.userRepository=userRepository;
        this.postRepository=postRepository;
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




















}
