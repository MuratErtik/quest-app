package com.project.questapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.questapp.entities.Comment;
import com.project.questapp.entities.Like;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.USer;
import com.project.questapp.repository.LikeRepository;
import com.project.questapp.requests.LikeCreateRequest;

@Service
public class LikeService {

    private LikeRepository likeRepository;
	private UserService userService;
	private PostService postService;

    public LikeService(LikeRepository likeRepository, UserService userService,PostService postService) {
		this.likeRepository = likeRepository;
		this.userService = userService;
		this.postService = postService;
	}

    public List<Like> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            return likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }
        else if (userId.isPresent()) {
            return likeRepository.findByUserId(userId.get());
        }
        else if (postId.isPresent()) {
            return likeRepository.findByPostid(postId.get());
        }
        else{
            return likeRepository.findAll();
        }      
    }

    public Like createOneLike(LikeCreateRequest request) {
        USer user = userService.getOneUserById(request.getUserId());
		Post post = postService.getOnePostById(request.getPostId());
		if(user != null && post != null) {
			Like likeToSave = new Like();
			likeToSave.setId(request.getId());
			likeToSave.setPost(post);
			likeToSave.setUser(user);
			return likeRepository.save(likeToSave);
		}else		
			return null;
    }

    

    public Like getOneLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public void deleteOneLikeById(Long likeId) {
        likeRepository.deleteById(likeId);
    }

}
