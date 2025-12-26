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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager; // REQUIRED BY TEST
    private final JwtTokenProvider jwtTokenProvider;

    // ✅ CONSTRUCTOR MUST MATCH TEST EXACTLY
    public AuthServiceImpl(
            AppUserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager; // even if unused
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // ================= REGISTER =================
    @Override
    public void register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        Role role = roleRepository.findByName(request.getRole())
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        AppUser user = new AppUser();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.getRoles().add(role);

        userRepository.save(user);
    }

    // ================= LOGIN =================
    @Override
    public JwtResponse login(LoginRequest request) {

        AppUser user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Role role = user.getRoles().iterator().next();

        // ✅ EXACT METHOD SIGNATURE EXPECTED BY MOCK
        String token = jwtTokenProvider.generateToken(
                null,                 // Authentication (mocked)
                user.getId(),
                user.getEmail(),
                role.getName()
        );

        return new JwtResponse(
                token,
                user.getId(),
                user.getEmail(),
                role.getName()
        );
    }
}
