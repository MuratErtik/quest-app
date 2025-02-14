package com.project.questapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.questapp.entities.Comment;
import com.project.questapp.entities.Post;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByUserIdAndPostId(Long userId,long postId);
    //findby ile bizim yazidigmiz degeri birlestirip arkada sorgusunu yapar!

    List<Comment> findByUserId(Long userId);

    List<Comment> findByPostId(Long postId);

    @Query(value = "select * from comment where post_id in :postIds", nativeQuery = true)
    List<Comment> findUserCommentByPostId(@Param("postIds") List<Long> postIds);


}
