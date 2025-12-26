package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import java.util.Date;

public class JwtTokenProvider {

    private final String secret;
    private final long expiry;

    public JwtTokenProvider(String secret, long expiry) {
        this.secret = secret;
        this.expiry = expiry;
    }

    public String generateToken(Authentication auth, Long id, String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", id)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiry))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
