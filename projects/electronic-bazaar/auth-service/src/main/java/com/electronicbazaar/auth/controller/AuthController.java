package com.electronicbazaar.auth.controller;

import com.electronicbazaar.auth.model.Customer;
import com.electronicbazaar.auth.model.RefreshToken;
import com.electronicbazaar.auth.repository.RefreshTokenRepository;
import com.electronicbazaar.auth.repository.CustomerRepository;
import com.electronicbazaar.auth.service.AuthService;
import com.electronicbazaar.auth.service.RefreshTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final CustomerRepository customerRepository;

    public AuthController(AuthService authService, RefreshTokenService refreshTokenService, RefreshTokenRepository refreshTokenRepository, CustomerRepository customerRepository) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
        this.refreshTokenRepository = refreshTokenRepository;
        this.customerRepository = customerRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> register(@RequestBody Customer c) {
        return ResponseEntity.ok(authService.register(c));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> body) {
        String email = body.get("email"); String password = body.get("password");
        return ResponseEntity.ok(authService.login(email, password));
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refresh(@RequestBody Map<String, String> body) {
        String token = body.get("refreshToken");
        RefreshToken rt = refreshTokenRepository.findByToken(token).orElseThrow(() -> new RuntimeException("Invalid refresh token"));
        refreshTokenService.verifyExpiration(rt);
        String newAccess = authService.login(rt.getCustomer().getEmail(), rt.getCustomer().getPassword()).get("accessToken");
        // rotate refresh token
        RefreshToken newRt = refreshTokenService.createRefreshToken(rt.getCustomer());
        refreshTokenRepository.delete(rt);
        return ResponseEntity.ok(Map.of("accessToken", newAccess, "refreshToken", newRt.getToken()));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody Map<String, String> body) {
        String email = body.get("email"); var c = customerRepository.findByEmail(email).orElse(null);
        if (c != null) refreshTokenService.deleteByCustomer(c);
        return ResponseEntity.ok().build();
    }
}
