package com.electronicbazaar.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Value("${JWT_SECRET:replace_this_with_a_very_long_secret}")
    private String jwtSecret;

    @Bean
    public ReactiveJwtDecoder reactiveJwtDecoder() {
        byte[] keyBytes = jwtSecret.getBytes();
        NimbusReactiveJwtDecoder decoder = NimbusReactiveJwtDecoder
                .withSecretKey(new javax.crypto.spec.SecretKeySpec(keyBytes, "HmacSHA256")).build();
        return decoder;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/api/auth/**").permitAll()
                        .anyExchange().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtDecoder(reactiveJwtDecoder())));
        return http.build();
    }
}
