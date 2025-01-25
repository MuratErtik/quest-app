package com.project.questapp.responses;

import com.project.questapp.entities.USer;

import lombok.Data;

@Data
public class UserResponse {
    Long id;
    String username;

    public UserResponse(USer entitiy){
        this.id= entitiy.getId();
        this.username=entitiy.getUsername();
    }
}
