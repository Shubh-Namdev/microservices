package com.electronicbazaar.order.repository;

import com.electronicbazaar.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> { }
