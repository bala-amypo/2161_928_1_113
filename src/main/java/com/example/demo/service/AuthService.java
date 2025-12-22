package com.example.demo.service;

import com.example.demo.dto.*;

public interface AuthService {
    void register(RegisterRequest request);
    JwtResponse login(LoginRequest request);
}
