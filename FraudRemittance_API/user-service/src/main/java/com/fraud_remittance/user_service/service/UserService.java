package com.fraud_remittance.user_service.service;

import com.fraud_remittance.user_service.dto.LoginRequest;
import com.fraud_remittance.user_service.dto.LoginResponse;
import com.fraud_remittance.user_service.dto.UserRegisterResponse;
import com.fraud_remittance.user_service.dto.UserRegistrationRequest;

public interface UserService {
    UserRegisterResponse register(UserRegistrationRequest request);

    LoginResponse login(LoginRequest request);
}
