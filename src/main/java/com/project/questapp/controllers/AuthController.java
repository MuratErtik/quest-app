package com.project.questapp.controllers;

import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.questapp.entities.USer;
import com.project.questapp.requests.UserRequest;
import com.project.questapp.security.JwtTokenProvider;
import com.project.questapp.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager,JwtTokenProvider jwtTokenProvider,UserService userService,PasswordEncoder passwordEncoder){
        this.authenticationManager=authenticationManager;
        this.jwtTokenProvider=jwtTokenProvider;
        this.userService=userService;
        this.passwordEncoder=passwordEncoder;
    }

    @PostMapping("/login")
    public String login(@RequestBody UserRequest loginRequest){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication auth= authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwtToken = jwtTokenProvider.generateJwtToken(auth);

        return jwtToken; //Bearer eklenebilirde basa 

    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequest registerRequest){
        if (userService.getOneUserByUsername(registerRequest.getUsername())!= null) {
            return new ResponseEntity<>("Username have already register!",HttpStatus.BAD_REQUEST);
        }
        USer user = new USer();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userService.saveOneUser(user);
        return new ResponseEntity<>("User has been registered successfully",HttpStatus.CREATED);

    }



}
