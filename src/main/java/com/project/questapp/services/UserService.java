package com.project.questapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.questapp.entities.USer;
import com.project.questapp.repository.UserRepository;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;

    }

    public List<USer> getAllUsers() {
        return userRepository.findAll();
    }

    public USer saveOneUser(USer newUser) {
        return userRepository.save(newUser);
    }

    public USer getOneUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
        //exeption eklenecek!
        
    }

    public USer updateOneUser(Long userId, USer newUser) {
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

    public void deleteOneUser(Long userId) {
        userRepository.deleteById(userId);
    }










}
