package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    private final Key signingKey;
    private final long expirationTime;

    // ✅ REQUIRED by tests
    public JwtTokenProvider(String secret, long expirationTime) {
        this.signingKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationTime = expirationTime;
    }

    // ✅ REQUIRED default constructor
    public JwtTokenProvider() {
        this.signingKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        this.expirationTime = 86400000;
    }

    // ✅ REQUIRED by tests
    public String generateToken(Authentication authentication,
                                Long userId,
                                String email,
                                String role) {

        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("roles", List.of(role))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(signingKey)
                .compact();
    }

    // ✅ REQUIRED by service
    public String generateToken(Long userId, String email, List<String> roles) {

        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(signingKey)
                .compact();
    }

    // ✅ REQUIRED by tests
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
