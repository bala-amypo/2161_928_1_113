package com.example.demo.service;

import com.example.demo.dto.*;

public interface AuthService {
    JwtResponse login(LoginRequest request);
    void register(RegisterRequest request);
}
