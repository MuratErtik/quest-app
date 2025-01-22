package com.project.questapp.responses;

import java.util.List;

import com.project.questapp.entities.Like;
import com.project.questapp.entities.Post;

import lombok.Data;

@Data
public class PostResponse {
    Long id;
    Long userId;
    String username;
    String text;
    String title;
    List<LikeResponse> postLikes;


    public PostResponse(Post entity,List<LikeResponse> likes){

        this.id=entity.getId();
        this.userId=entity.getUser().getId();
        this.username=entity.getUser().getUsername();
        this.text=entity.getText();
        this.title=entity.getTitle();

        this.postLikes=likes;
    }
}
