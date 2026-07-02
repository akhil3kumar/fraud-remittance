package com.fraud_remittance.user_service.jwt;

import com.fraud_remittance.user_service.entity.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class JwtUtil {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private Long expirationTime;

    @Value("${security.jwt.refresh-expiration-time}")
    private Long refreshExpirationTime;

    public String generateAccessToken(Users user) {

        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(user.getEmail())
                .setClaims(getAccessClaims(user))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() +expirationTime))
                .signWith(getSignInKey(),SignatureAlgorithm.HS256)
                .compact();

    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Map<String , Object> getAccessClaims(Users user) {
        Map<String , Object> claims = new HashMap<>();
        claims.put("role", user.getRole().name());
        claims.put("id", user.getId());
        claims.put("tokenType", "ACCESS");
        return claims;
    }

    public String generateRefreshToken(Users user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("tokenType", "REFRESH");
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(user.getEmail())
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() +refreshExpirationTime))
                .signWith(getSignInKey(),SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Claims extractAllClaims(String token) {

        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        } catch (Exception ex) {
            throw new RuntimeException("Invalid JWT token");
        }
    }

    public boolean isTokenExpired(String token) {
        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    public boolean isTokenValid(String token, String email) {
        return extractUsername(token).equals(email)
                && !isTokenExpired(token);
    }

    public String extractRole(String token) {
        return extractAllClaims(token)
                .get("role", String.class);
    }

    public Long extractUserId(String token) {
        return extractAllClaims(token)
                .get("id", Long.class);
    }

    public LocalDateTime getRefreshExpirationTime() {
        return LocalDateTime.now()
                .plusSeconds(refreshExpirationTime / 1000);
    }
}
