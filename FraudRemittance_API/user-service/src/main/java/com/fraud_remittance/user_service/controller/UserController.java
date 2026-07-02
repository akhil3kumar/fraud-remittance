package com.fraud_remittance.user_service.controller;

import com.fraud_remittance.user_service.dto.*;
import com.fraud_remittance.user_service.service.RefreshTokenService;
import com.fraud_remittance.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping
    public ResponseEntity<UserRegisterResponse> register(@RequestBody @Valid UserRegistrationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(request));
    }

    @PostMapping
    public ResponseEntity<LoginResponse> register(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@RequestBody RefreshTokenRequest request) {

        return ResponseEntity.ok(
                refreshTokenService.refreshToken(request)
        );
    }

    @GetMapping("/test")
    public String user(){
        return "user";
    }
}
