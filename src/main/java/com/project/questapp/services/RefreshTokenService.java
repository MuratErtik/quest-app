package com.project.questapp.services;

import org.springframework.stereotype.Service;

import com.project.questapp.entities.RefreshToken;
import com.project.questapp.repository.RefreshTokenRepository;

@Service
public class RefreshTokenService {
    private RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository){
        this.refreshTokenRepository=refreshTokenRepository;
    }

    public boolean isRefreshExpired(RefreshToken refreshToken){
        return false;
    }

}
