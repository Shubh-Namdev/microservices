package com.electronicbazaar.controller;

import com.electronicbazaar.config.JwtTokenProvider;
import com.electronicbazaar.dto.AuthRequest;
import com.electronicbazaar.dto.AuthResponse;
import com.electronicbazaar.model.Customer;
import com.electronicbazaar.model.RefreshToken;
import com.electronicbazaar.repository.CustomerRepository;
import com.electronicbazaar.repository.RefreshTokenRepository;
import com.electronicbazaar.service.AuthService;
import com.electronicbazaar.service.RefreshTokenService;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenService refreshTokenService;
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomerRepository customerRepository;

    public AuthController(AuthService authService, RefreshTokenRepository refreshTokenRepository,
            RefreshTokenService refreshTokenService,
            JwtTokenProvider jwtTokenProvider,
            CustomerRepository customerRepository) {
        this.authService = authService;
        this.refreshTokenRepository = refreshTokenRepository;
        this.refreshTokenService = refreshTokenService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> register(@RequestBody Customer c) {
        return ResponseEntity.ok(authService.register(c));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody Map<String, String> body) {
        String refreshToken = body.get("refreshToken");
        RefreshToken token = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        refreshTokenService.verifyExpiration(token);

        String newAccessToken = jwtTokenProvider.generateToken(token.getCustomer().getEmail());
        // Optionally rotate refresh token: create a new one and delete old
        RefreshToken newRefresh = refreshTokenService.createRefreshToken(token.getCustomer());
        refreshTokenRepository.delete(token);

        return ResponseEntity.ok(new AuthResponse(newAccessToken, newRefresh.getToken()));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        Customer c = customerRepository.findByEmail(email).orElse(null);
        if (c != null) {
            refreshTokenService.deleteByCustomer(c);
        }
        return ResponseEntity.ok().build();
    }
}
