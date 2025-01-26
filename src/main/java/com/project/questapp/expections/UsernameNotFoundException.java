package com.project.questapp.expections;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(){
        super();
    }

    public UsernameNotFoundException(String message){
        super(message);
    }
}
