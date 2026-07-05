package com.fraud_remittance.user_service.dto;

public record LoginRequest(
        String email,
        String password
) {}
