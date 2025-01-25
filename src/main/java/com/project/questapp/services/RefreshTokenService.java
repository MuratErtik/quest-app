package com.project.questapp.services;

import java.time.Instant;
import java.util.Date;
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
    public String createRefreshToken(Optional<USer>  user) {
        RefreshToken refreshToken = new RefreshToken();

        user = refreshTokenRepository.findUserByUserId(user.get().getId());

        if (user.isPresent()) {
            
            refreshToken.setExpiryDate((java.sql.Date) Date.from(Instant.now().plusSeconds(expireSeconds)));
            refreshTokenRepository.save(refreshToken);
            return refreshToken.getToken();

        }
        else{
            refreshToken.setUser(user.get());
            refreshToken.setToken(UUID.randomUUID().toString());
            refreshToken.setExpiryDate((java.sql.Date) Date.from(Instant.now().plusSeconds(expireSeconds)));
            refreshTokenRepository.save(refreshToken);
            return refreshToken.getToken();

        }


    }

}
