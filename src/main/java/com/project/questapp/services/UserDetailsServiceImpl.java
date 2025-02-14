package com.project.questapp.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.questapp.entities.USer;
import com.project.questapp.repository.UserRepository;
import com.project.questapp.security.JwtUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        USer user = userRepository.findByUsername(username);


        return JwtUserDetails.create(user);
    }

    public UserDetails loadUserbyId(Long id){
        USer user= userRepository.findById(id).get();
        return JwtUserDetails.create(user);
    }

}
