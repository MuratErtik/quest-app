package com.project.questapp.security;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
    @Value("${questapp.app.secret}")
    private String APP_SECRET;
    @Value("${questapp.app.expires.in}")
    private Long EXPIRES_IN;

    //authentication  bir kullanıcı sisteme giriş yaptıysa, bu nesne onun kim olduğunu ve hangi yetkilere sahip olduğunu tutar.
    public String generateJwtToken(Authentication authentication){
        JwtUserDetails userDetails=  (JwtUserDetails) authentication.getPrincipal();//kullanici bilgilerini alir
        Date expirDate = new Date(new Date(0).getTime()+EXPIRES_IN); 
        return Jwts.builder().setSubject(Long.toString(userDetails.getId()))
                                                                  .setIssuedAt(new Date(0))
                                                                  .setExpiration(expirDate)
                                                                  .signWith(SignatureAlgorithm.HS512,APP_SECRET)
                                                                  .compact();
    }
    /*
     * setSubject:
        Kullanıcının kimliği (örneğin, ID) token'in içine eklenir.
        Bu, token'in kimin için oluşturulduğunu belirtir.
     */


}
