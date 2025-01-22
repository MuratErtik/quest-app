package com.project.questapp.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.project.questapp.entities.USer;

import lombok.Data;

//authorize islemleri icin kullanilacak classtir detayli tum bilgiler yer almaz!  
@Data
public class JwtUserDetails implements UserDetails {
    public Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    private JwtUserDetails(Long id,String username,String password,Collection<? extends GrantedAuthority> authorities){
        this.id=id;
        this.username=username;
        this.password=password;
        this.authorities=authorities;
    }

    public static JwtUserDetails create(USer user){
        List<GrantedAuthority> authoritiesList=new ArrayList<>();
        authoritiesList.add(new SimpleGrantedAuthority("user"));
        return new  JwtUserDetails(user.getId(),user.getUsername(), user.getPassword(), authoritiesList);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
    }

}
