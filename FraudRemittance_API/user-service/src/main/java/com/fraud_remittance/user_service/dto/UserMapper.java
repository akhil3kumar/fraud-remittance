package com.fraud_remittance.user_service.dto;

import com.fraud_remittance.user_service.entity.UserRole;
import com.fraud_remittance.user_service.entity.UserStatus;
import com.fraud_remittance.user_service.entity.Users;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class UserMapper {

    public Users toEntity(UserRegistrationRequest request, String password) {
        return Users.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .contactNumber(request.phoneNumber())
                .password(password)
                .role(UserRole.ADMIN)
                .status(UserStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public UserRegisterResponse toResponse(Users savedUser) {
        return UserRegisterResponse.builder()
                .id(savedUser.getId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .email(savedUser.getEmail())
                .phoneNumber(savedUser.getContactNumber())
                .role(savedUser.getRole().name())
                .status(savedUser.getStatus().name())
                .createdAt(savedUser.getCreatedAt())
                .build();
    }

    public LoginResponse toLoginResponse(Users user, String token, String refreshToken) {
        return LoginResponse.builder()
                .userId(user.getId())
                .name((user.getFirstName()+ " "+ user.getLastName()).trim())
                .email(user.getEmail())
                .phoneNumber(user.getContactNumber())
                .role(user.getRole().name())
                .accessToken(token)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .loginTime(LocalDateTime.now())
                .build();

    }
}
