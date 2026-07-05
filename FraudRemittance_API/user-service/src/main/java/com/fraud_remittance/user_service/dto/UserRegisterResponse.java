package com.fraud_remittance.user_service.dto;

import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record UserRegisterResponse (
        Long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String role,
        String status,
        LocalDateTime createdAt
){
}
