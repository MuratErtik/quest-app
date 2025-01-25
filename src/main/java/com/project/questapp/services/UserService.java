package com.project.questapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.questapp.entities.Comment;
import com.project.questapp.entities.Like;
import com.project.questapp.entities.USer;
import com.project.questapp.repository.CommentRepository;
import com.project.questapp.repository.LikeRepository;
import com.project.questapp.repository.PostRepository;
import com.project.questapp.repository.UserRepository;

@Service
public class UserService {
    UserRepository userRepository;
    CommentRepository commentRepository;
    LikeRepository likeRepository;
    PostRepository postRepository;

    public UserService(UserRepository userRepository,CommentRepository commentRepository,LikeRepository likeRepository,PostRepository postRepository){
        this.userRepository=userRepository;
        this.commentRepository=commentRepository;
        this.likeRepository=likeRepository;
        this.postRepository=postRepository;

    }

    public List<USer> getAllUsers() {
        return userRepository.findAll();
    }

    public USer saveOneUser(USer newUser) {
        return userRepository.save(newUser);
    }

    public USer getOneUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
        //exeption eklenecek!
        
    }

    public USer updateOneUser(Long userId, USer newUser) {
        Optional<USer> user = userRepository.findById(userId);
        if (user.isPresent()) {
            USer foundUser = user.get();
            foundUser.setUsername(newUser.getUsername());
            foundUser.setPassword(newUser.getPassword());
            userRepository.save(foundUser);
            return foundUser;
        }
        else{
            return null;//degistir
        }
    }

    public void deleteOneUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public USer getOneUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<Object> getUserActivity(Long userId) {
        List<Long> postIds =  postRepository.findTopByUserId(userId);
        if (postIds.isEmpty()) {
            return null;
        }
        List<Comment> comments= commentRepository.findUserCommentByPostId(postIds);
        List<Like> likes= likeRepository.findUserLikeByPostId(postIds);
        List<Object> result= new ArrayList<>();
        result.addAll(comments);
        result.addAll(likes);
        return result;


    }

    










}
