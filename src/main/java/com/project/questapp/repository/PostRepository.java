package com.project.questapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.questapp.entities.Post;

public interface PostRepository extends JpaRepository<Post,Long>{

    List<Post> findByUserId(Long long1);
    //findby ile bizim yazidigmiz degeri birlestirip arkada sorgusunu yapar!

    @Query(value = "select id from post where user_id = :userId order by create_date desc limit 10 ",nativeQuery = true)
    List<Long> findTopByUserId(@Param("userId") Long userId);
    //nativeQuery = true dediğimiz için burada tamamen veritabanının anlayacağı türde, doğrudan SQL sorgusu yazıyoruz.

}
