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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    @Override
    public void saveToken(Users user, String refreshToken) {
        RefreshToken refreshTokenObj = new RefreshToken();
        refreshTokenObj.setRefreshToken(refreshToken);
        refreshTokenObj.setUser(user);

        Date expiryDate = jwtUtil.extractAllClaims(refreshToken)
                .getExpiration();

        refreshTokenObj.setExpiryDate(
                expiryDate.toInstant()
                        .atZone(ZoneId.of("UTC"))
                        .toLocalDateTime()
        );

        refreshTokenRepository.save(refreshTokenObj);
    }

    @Transactional
    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {

        if (!jwtUtil.validateToken(request.refreshToken())) {
            throw new BadCredentialsException("Invalid refresh token");
        }

        RefreshToken refreshTokenUser = refreshTokenRepository
                .findByRefreshToken(request.refreshToken())
                .orElseThrow(() -> new BadCredentialsException("Invalid refresh token"));

        String email = jwtUtil.extractUsername(request.refreshToken());
        if (!email.equals(refreshTokenUser.getUser().getEmail())) {
            throw new BadCredentialsException("Token mismatch");
        }
        if (refreshTokenUser.getExpiryDate()
                .isBefore(LocalDateTime.now())) {
            refreshTokenRepository.delete(refreshTokenUser);
            throw new BadCredentialsException("Refresh token expired");
        }
        Users user = refreshTokenUser.getUser();

        String accessToken = jwtUtil.generateAccessToken(user);

        String newRefreshToken = jwtUtil.generateRefreshToken(user);

        deleteRefreshToken(user);

        saveToken(user, newRefreshToken);

        return new RefreshTokenResponse(
                accessToken,
                newRefreshToken,
                "Bearer"
        );
    }

    @Override
    public void deleteRefreshToken(Users user) {
        refreshTokenRepository.deleteByUser(user);
    }



}

