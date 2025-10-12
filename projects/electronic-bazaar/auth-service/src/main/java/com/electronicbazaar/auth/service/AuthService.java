package com.electronicbazaar.auth.service;

import com.electronicbazaar.auth.model.Customer;
import com.electronicbazaar.auth.repository.CustomerRepository;
import com.electronicbazaar.auth.config.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;

    public AuthService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, RefreshTokenService refreshTokenService) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.refreshTokenService = refreshTokenService;
    }

    public Customer register(Customer c) {
        c.setPassword(passwordEncoder.encode(c.getPassword()));
        return customerRepository.save(c);
    }

    public java.util.Map<String, String> login(String email, String password) {
        Customer c = customerRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if (!passwordEncoder.matches(password, c.getPassword())) throw new RuntimeException("Invalid credentials");
        String access = jwtTokenProvider.generateToken(c.getEmail());
        var refresh = refreshTokenService.createRefreshToken(c);
        return java.util.Map.of("accessToken", access, "refreshToken", refresh.getToken());
    }
}
