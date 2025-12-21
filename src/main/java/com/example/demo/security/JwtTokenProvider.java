package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public String generateToken(Object authentication,
                                Long userId,
                                String email,
                                String role) {
        return "dummy-jwt-token";
    }
}
