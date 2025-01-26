package com.project.questapp.services;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.questapp.entities.RefreshToken;
import com.project.questapp.entities.USer;
import com.project.questapp.repository.RefreshTokenRepository;

@Service
public class RefreshTokenService {
    @Value("${refresh.token.expires.in}")
    Long expireSeconds;

    private RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public boolean isRefreshExpired(RefreshToken refreshToken) {
        return refreshToken.getExpiryDate().before(new Date());
    }

    @SuppressWarnings("unchecked")
    public String createRefreshToken(USer  user) {
        RefreshToken refreshToken = new RefreshToken();
        user = refreshTokenRepository.findUserByUserId(user.getId());

        

        if (user!=null) {
            
            refreshToken.setExpiryDate((java.sql.Date) Date.from(Instant.now().plusSeconds(expireSeconds)));
            refreshTokenRepository.save(refreshToken);
            return refreshToken.getToken();

        }
        else{
            refreshToken.setUser(user);
            refreshToken.setToken(UUID.randomUUID().toString());
            refreshToken.setExpiryDate((java.sql.Date) Date.from(Instant.now().plusSeconds(expireSeconds)));
            refreshTokenRepository.save(refreshToken);
            return refreshToken.getToken();

        }


    }

    public RefreshToken getByUser(Long userId) {
        return refreshTokenRepository.findUserById(userId);

        
    }

}
