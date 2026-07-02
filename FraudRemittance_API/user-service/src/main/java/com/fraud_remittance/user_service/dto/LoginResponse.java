package com.fraud_remittance.user_service.dto;

import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record LoginResponse(
        Long userId,
        String name,
        String email,
        String phoneNumber,
        String role,
        String accessToken,
        String refreshToken,
        String tokenType,
        LocalDateTime loginTime
) {
}
