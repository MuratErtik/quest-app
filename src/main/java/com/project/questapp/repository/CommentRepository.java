package com.project.questapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.questapp.entities.Comment;
import com.project.questapp.entities.Post;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByUserIdAndPostId(Long long1,long long2);
    //findby ile bizim yazidigmiz degeri birlestirip arkada sorgusunu yapar!

    List<Comment> findByUserId(Long userId);

    List<Comment> findByPostid(Long postId);


}
