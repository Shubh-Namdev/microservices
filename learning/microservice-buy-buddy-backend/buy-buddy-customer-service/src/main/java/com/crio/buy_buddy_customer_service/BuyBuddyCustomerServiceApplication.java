package com.crio.buy_buddy_customer_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BuyBuddyCustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuyBuddyCustomerServiceApplication.class, args);
	}

}
