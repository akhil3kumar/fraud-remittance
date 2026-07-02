package com.fraud_remittance.user_service.repository;

import com.fraud_remittance.user_service.entity.RefreshToken;
import com.fraud_remittance.user_service.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    void deleteByUser(Users user);
}
