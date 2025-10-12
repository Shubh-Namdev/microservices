package com.electronicbazaar.auth.repository;

import com.electronicbazaar.auth.model.RefreshToken;
import com.electronicbazaar.auth.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByCustomer(Customer customer);
}
