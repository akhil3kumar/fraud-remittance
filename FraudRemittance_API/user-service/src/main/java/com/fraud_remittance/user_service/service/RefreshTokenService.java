package com.fraud_remittance.user_service.service;

import com.fraud_remittance.user_service.dto.RefreshTokenRequest;
import com.fraud_remittance.user_service.dto.RefreshTokenResponse;
import com.fraud_remittance.user_service.entity.Users;

public interface RefreshTokenService {
    void saveToken(Users user,String refreshToken);

    RefreshTokenResponse refreshToken(RefreshTokenRequest request);

    void deleteRefreshToken(Users user);
}
