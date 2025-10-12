package com.electronicbazaar.service;

import com.electronicbazaar.dto.AuthRequest;
import com.electronicbazaar.dto.AuthResponse;
import com.electronicbazaar.model.Customer;
import com.electronicbazaar.model.RefreshToken;
import com.electronicbazaar.repository.CustomerRepository;
import com.electronicbazaar.config.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;

    public AuthService(CustomerRepository customerRepository,
            PasswordEncoder passwordEncoder,
            JwtTokenProvider jwtTokenProvider,
            RefreshTokenService refreshTokenService) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.refreshTokenService = refreshTokenService;
    }

    public AuthResponse login(AuthRequest request) {
        Customer c = customerRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if (!passwordEncoder.matches(request.getPassword(), c.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        String accessToken = jwtTokenProvider.generateToken(c.getEmail());
        RefreshToken refresh = refreshTokenService.createRefreshToken(c);
        return new AuthResponse(accessToken, refresh.getToken());
    }

    public Customer register(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }
}
