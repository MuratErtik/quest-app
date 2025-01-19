package com.project.questapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.questapp.entities.Post;

public interface PostRepository extends JpaRepository<Post,Long>{

    List<Post> findByUserId(Long long1);
    //findby ile bizim yazidigmiz degeri birlestirip arkada sorgusunu yapar!

}
