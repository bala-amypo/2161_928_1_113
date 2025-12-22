@Override
public JwtResponse login(LoginRequest request) {

    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            )
    );

    AppUser user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

    String token = jwtTokenProvider.generateToken(
            user,                 // Object
            user.getId(),         // Long
            user.getEmail(),      // String
            user.getRole().getName() // String
    );

    return new JwtResponse(
            token,
            user.getId(),
            user.getEmail(),
            user.getRole().getName()
    );
}
