package com.project.questapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.questapp.entities.USer;


public interface UserRepository extends JpaRepository<USer,Long>{
 
    USer findByUsername(String username);
    //jparep interface i query atmaya yarar icindeki metotlar sayesinde

}
