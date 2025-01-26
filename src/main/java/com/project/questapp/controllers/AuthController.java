package com.project.questapp.controllers;

import java.io.ObjectInputFilter.Status;
import java.util.Optional;

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

import com.project.questapp.entities.RefreshToken;
import com.project.questapp.entities.USer;
import com.project.questapp.requests.RefreshRequest;
import com.project.questapp.requests.UserRequest;
import com.project.questapp.responses.AuthResponse;
import com.project.questapp.security.JwtTokenProvider;
import com.project.questapp.services.RefreshTokenService;
import com.project.questapp.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private RefreshTokenService refreshTokenService;

    public AuthController(AuthenticationManager authenticationManager,JwtTokenProvider jwtTokenProvider,UserService userService,PasswordEncoder passwordEncoder,RefreshTokenService refreshTokenService){
        this.authenticationManager=authenticationManager;
        this.jwtTokenProvider=jwtTokenProvider;
        this.userService=userService;
        this.passwordEncoder=passwordEncoder;
        this.refreshTokenService=refreshTokenService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody UserRequest loginRequest){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication auth= authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        USer user = userService.getOneUserByUsername(loginRequest.getUsername());

        AuthResponse authResponse= new AuthResponse();
        authResponse.setAccessToken(jwtToken);
        authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
        authResponse.setUserId(user.getId());

        return authResponse ; //Bearer eklenebilirde basa 

    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserRequest registerRequest){
        AuthResponse authResponse = new AuthResponse();
        if (userService.getOneUserByUsername(registerRequest.getUsername())!= null) {
            authResponse.setMessage("Username have already register!");
            return new ResponseEntity<>(authResponse,HttpStatus.BAD_REQUEST);
        }
        //reg olduysa login olmus kabul ettik
        USer user = new USer();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userService.saveOneUser(user);

        UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(authResponse, user);
        Authentication auth= authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);


        authResponse.setMessage("User has been registered successfully");
        authResponse.setAccessToken(jwtToken);
        authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
        authResponse.setUserId(user.getId());
        return new ResponseEntity<>(authResponse,HttpStatus.CREATED);   

    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshRequest refreshRequest){
        AuthResponse authResponse = new AuthResponse();
        RefreshToken refreshToken= refreshTokenService.getByUser(refreshRequest.getUserId());
        //gelen tokenla dbdeki token aynimi kontrol et 

        if (refreshToken.getToken().equals(refreshRequest.getRefreshToken()) && refreshTokenService.isRefreshExpired(refreshToken)) {
            //yeni access bir token olusturt
            USer user = refreshToken.getUser();
            
            String jwtToken = jwtTokenProvider.generateJwtTokenByUsername(user.getId());
            authResponse.setUserId(user.getId());


            authResponse.setMessage("User has been refreshed successfully");
            authResponse.setAccessToken(jwtToken);
            authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
            authResponse.setUserId(user.getId());
            return new ResponseEntity<>(authResponse,HttpStatus.OK);  
        }
        else{
            authResponse.setMessage("refresh token is invalid!");
            return new ResponseEntity<>(authResponse,HttpStatus.UNAUTHORIZED);
        }
    }



}
