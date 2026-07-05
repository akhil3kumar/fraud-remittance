package com.fraud_remittance.user_service.service.impl;

import com.fraud_remittance.user_service.dto.*;
import com.fraud_remittance.user_service.entity.UserStatus;
import com.fraud_remittance.user_service.entity.Users;
import com.fraud_remittance.user_service.jwt.JwtUtil;
import com.fraud_remittance.user_service.service.RefreshTokenService;
import com.fraud_remittance.user_service.service.UserService;
import com.fraud_remittance.user_service.repository.UserRepositroy;
import exception.BadCredentialsException;
import exception.ResourceAlreadyExistExeption;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepositroy userRepositroy;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Transactional
    @Override
    public UserRegisterResponse register(UserRegistrationRequest request) {
        log.info("Registering user with email {}", request.email());
        // validate request by mail and phone number
        validateUserByEmail(request.email());
        validateUserByPhoneNumber(request.phoneNumber());

        Users user = userMapper.toEntity(request,passwordEncoder.encode(request.password()));

        Users savedUser = userRepositroy.save(user);
        log.info("User registered successfully with id {}", savedUser.getId());
        return userMapper.toResponse(savedUser);
        
    }

    @Transactional
    @Override
    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password()));

        Users user = userRepositroy.findByEmail(request.email())
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));
        if (user.getStatus() != UserStatus.ACTIVE) {
            throw new BadCredentialsException("Account is inactive");
        }
        String accessToken = jwtUtil.generateAccessToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user);

        refreshTokenService.deleteRefreshToken(user);

        refreshTokenService.saveToken(user, refreshToken);

        return userMapper.toLoginResponse(user,accessToken,refreshToken);
    }

    private void validateUserByPhoneNumber(String phoneNumber) {
        if(userRepositroy.existsByPhoneNumber(phoneNumber)){
            throw new ResourceAlreadyExistExeption("User with phone number already exists");
        }
    }

    private void validateUserByEmail(String email) {
        if(userRepositroy.existsByEmail(email)){
            throw new ResourceAlreadyExistExeption("User with email already exists");
        }
    }
}
