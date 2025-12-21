package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestBody RegisterRequest req) {
        authService.register(req);
        return new ApiResponse(true, "Registered");
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest req) {
        return authService.login(req);
    }
}
