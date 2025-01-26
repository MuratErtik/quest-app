package com.project.questapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.questapp.entities.Comment;
import com.project.questapp.entities.RefreshToken;
import com.project.questapp.entities.USer;

public interface RefreshTokenRepository extends JpaRepository {
    USer findUserByUserId(Long userId);
    

}
