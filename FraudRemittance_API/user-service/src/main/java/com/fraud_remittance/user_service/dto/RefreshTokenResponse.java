package com.fraud_remittance.user_service.dto;

public record RefreshTokenResponse(
        String accessToken,
        String tokenType
) {}
