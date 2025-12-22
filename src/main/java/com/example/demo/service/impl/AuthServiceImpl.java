package com.example.demo.service.impl;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.AppUser;
import com.example.demo.entity.Role;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.repository.RoleRepository;
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

        // 1️⃣ Email check
        if (userRepo.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // 2️⃣ FIXED ROLE MAPPING (🔥 THIS FIXES 500)
        Role role = roleRepo.findByName("ROLE_" + req.getRole())
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        // 3️⃣ Create user
        AppUser user = new AppUser(
                req.getFullName(),
                req.getEmail(),
                encoder.encode(req.getPassword())
        );

        // 4️⃣ Assign role
        user.getRoles().add(role);

        // 5️⃣ Save
        userRepo.save(user);
    }

    // ================= LOGIN =================
    @Override
    public JwtResponse login(LoginRequest req) {

        // 1️⃣ Authenticate
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(),
                        req.getPassword()
                )
        );

        // 2️⃣ Load user
        AppUser user = userRepo.findByEmail(req.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // 3️⃣ Get role
        String role = user.getRoles().iterator().next().getName();

        // 4️⃣ Generate JWT
        String token = jwtProvider.generateToken(
                null,
                user.getId(),
                user.getEmail(),
                role
        );

        // 5️⃣ Response
        return new JwtResponse(
                token,
                user.getId(),
                user.getEmail(),
                role
        );
    }
}
