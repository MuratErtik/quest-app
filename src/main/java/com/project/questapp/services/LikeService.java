package com.project.questapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.questapp.entities.Like;
import com.project.questapp.requests.LikeCreateRequest;

@Service
public class LikeService {

    

    public List<Like> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
        return null;
    }

    public Like createOneLike(LikeCreateRequest request) {
        return null;
    }

    public Like getOneLikeById(Long likeId) {
        return null;
    }

    public void deleteOneLikeById(Long likeId) {
        return;
    }

}
