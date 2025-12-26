package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthServiceImpl {

    private final AppUserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtTokenProvider jwt;

    public AuthServiceImpl(AppUserRepository u, RoleRepository r,
                           PasswordEncoder e, AuthenticationManager a,
                           JwtTokenProvider j) {
        userRepo = u; roleRepo = r; encoder = e; authManager = a; jwt = j;
    }

    public void register(RegisterRequest req) {
        if (userRepo.existsByEmail(req.getEmail()))
            throw new IllegalArgumentException("Duplicate");

        Role role = roleRepo.findByName(req.getRole())
                .orElseThrow(() -> new IllegalArgumentException("Role"));

        AppUser user = new AppUser();
        user.setEmail(req.getEmail());
        user.setFullName(req.getFullName());
        user.setPassword(encoder.encode(req.getPassword()));
        user.getRoles().add(role);

        userRepo.save(user);
    }

    public JwtResponse login(LoginRequest req) {
        Authentication auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
        );

        AppUser user = userRepo.findByEmail(req.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        String role = user.getRoles().iterator().next().getName();
        String token = jwt.generateToken(auth, user.getId(), user.getEmail(), role);

        return new JwtResponse(token, user.getId(), user.getEmail(), role);
    }
}
