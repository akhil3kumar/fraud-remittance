package com.fraud_remittance.user_service.service.impl;

import com.fraud_remittance.user_service.dto.RefreshTokenRequest;
import com.fraud_remittance.user_service.dto.RefreshTokenResponse;
import com.fraud_remittance.user_service.entity.RefreshToken;
import com.fraud_remittance.user_service.entity.Users;
import com.fraud_remittance.user_service.jwt.JwtUtil;
import com.fraud_remittance.user_service.service.RefreshTokenService;
import com.fraud_remittance.user_service.repository.RefreshTokenRepository;
import exception.BadCredentialsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void saveToken(Users user, String refreshToken) {
        RefreshToken refreshTokenObj = new RefreshToken();
        refreshTokenObj.setToken(refreshToken);
        refreshTokenObj.setUser(user);

        Date expiryDate = jwtUtil.extractAllClaims(refreshToken)
                .getExpiration();

        refreshTokenObj.setExpiryDate(
                expiryDate.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime()
        );
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
        RefreshToken refreshToken = refreshTokenRepository
                .findByRefreshToken(request.refreshToken())
                .orElseThrow(() -> new BadCredentialsException("Invalid refresh token"));

        if (refreshToken.getExpiryDate()
                .isBefore(LocalDateTime.now())) {
            throw new BadCredentialsException("Refresh token expired");
        }

        String accessToken = jwtUtil.generateAccessToken(refreshToken.getUser());
        return new RefreshTokenResponse(
                accessToken,
                "Bearer"
        );
    }

    @Override
    public void deleteRefreshToken(Users user) {
        refreshTokenRepository.deleteByUser(user);
    }

}

