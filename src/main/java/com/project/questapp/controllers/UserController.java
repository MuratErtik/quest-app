package com.project.questapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.questapp.entities.USer;
import com.project.questapp.repository.UserRepository;
import com.project.questapp.responses.UserResponse;
import com.project.questapp.services.UserService;

@RestController
@RequestMapping("/users")

public class UserController {
    //controller gelen istekleri karsilayan methodlari iceren classlardir.

    private UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
        //spring boot bizim icin arka planda bulacak ve constructor injection yapacak.

    }

    @GetMapping
    //path verilmezse /users icin calismaya devam eder
    public List<USer> getAllUser(){
        return userService.getAllUsers();
    }
    

    @PostMapping
    public USer createUser(@RequestBody USer newUser){
        return userService.saveOneUser(newUser);
    }

    @GetMapping("/{userId}") //requestmapping e verilene ekler
    public UserResponse getOneUser(@PathVariable Long userId){
        USer user = userService.getOneUserById(userId);
        if (user==null) {
            throw new com.project.questapp.expections.UsernameNotFoundException();
        }
        
        return new UserResponse(userService.getOneUserById(userId));
        
    }
    @PutMapping("/{userId}")
    //varolan idli useri degistir
    public USer updateUser(@PathVariable Long userId,@RequestBody USer newUser){

        return userService.updateOneUser(userId,newUser);

    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteOneUser(userId);

    }

    @GetMapping("/activity/{userId}")
    public List<Object> getUserActivity(@PathVariable Long userId){
        return userService.getUserActivity(userId);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void handleUserNotFound(){
        
    }



}
