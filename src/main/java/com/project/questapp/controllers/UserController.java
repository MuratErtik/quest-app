package com.project.questapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.questapp.entities.USer;
import com.project.questapp.repository.UserRepository;

@RestController
@RequestMapping("/users")

public class UserController {
    //controller gelen istekleri karsilayan methodlari iceren classlardir.

    private UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository=userRepository;
        //spring boot bizim icin arka planda bulacak ve constructor injection yapacak.

    }

    @GetMapping
    //path verilmezse /users icin calismaya devam eder
    public List<USer> getAllUser(){
        return userRepository.findAll();
    }
    

    @PostMapping
    public USer createUser(@RequestBody USer newUser){
        return userRepository.save(newUser);
    }

    @GetMapping("/{userId}") //requestmapping e verilene ekler
    public USer getOneUser(@PathVariable Long userId){
        //user olmayabilir exception ekle
        return userRepository.findById(userId).orElse(null);
        
    }
    @PutMapping("/{userId}")
    //varolan idli useri degistir
    public USer updateUser(@PathVariable Long userId,@RequestBody USer newUser){

        Optional<USer> user = userRepository.findById(userId);
        if (user.isPresent()) {
            USer foundUser = user.get();
            foundUser.setUsername(newUser.getUsername());
            foundUser.setPassword(newUser.getPassword());
            userRepository.save(foundUser);
            return foundUser;
        }
        else{
            return null;//degistir
        }

    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userRepository.deleteById(userId);

    }



}
