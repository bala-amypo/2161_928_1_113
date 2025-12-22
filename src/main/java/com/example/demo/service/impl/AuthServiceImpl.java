package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtTokenProvider jwtProvider;

    public AuthServiceImpl(AppUserRepository userRepo,
                           RoleRepository roleRepo,
                           PasswordEncoder encoder,
                           AuthenticationManager authManager,
                           JwtTokenProvider jwtProvider) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwtProvider = jwtProvider;
    }

    // ================= REGISTER =================
    @Override
    public void register(RegisterRequest req) {

        // Email already exists check
        if (userRepo.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Convert role safely
        String roleName = "ROLE_" + req.getRole().toUpperCase();

        Role role = roleRepo.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));

        // Create user
        AppUser user = new AppUser(
                req.getFullName(),
                req.getEmail(),
                encoder.encode(req.getPassword())
        );

        user.getRoles().add(role);
        userRepo.save(user);
    }

    // ================= LOGIN =================
    @Override
    public JwtResponse login(LoginRequest req) {

        // Authenticate user
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(),
                        req.getPassword()
                )
        );

        // Fetch user
        AppUser user = userRepo.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String role = user.getRoles().iterator().next().getName();

        String token = jwtProvider.generateToken(
                null,
                user.getId(),
                user.getEmail(),
                role
        );

        return new JwtResponse(
                token,
                user.getId(),
                user.getEmail(),
                role
        );
    }
}
