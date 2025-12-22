@Bean
public SecurityFilterChain filterChain(
        HttpSecurity http,
        JwtAuthenticationFilter jwtFilter
) throws Exception {

    http
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(
                "/auth/login",
                "/auth/register",
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-ui.html"
            ).permitAll()
            .anyRequest().authenticated()
        )
        .addFilterBefore(jwtFilter,
                UsernamePasswordAuthenticationFilter.class);

    return http.build();
}
