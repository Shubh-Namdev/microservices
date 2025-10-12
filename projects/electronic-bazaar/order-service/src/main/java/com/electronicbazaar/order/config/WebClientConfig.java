package com.electronicbazaar.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    
    @Bean
    public WebClient customerWebClient(WebClient.Builder builder) {
        String customerUrl = System.getenv()
                .getOrDefault("CUSTOMER_SERVICE_URL", "http://customer-service:8083");
        return builder.baseUrl(customerUrl).build();
    }

    @Bean
    public WebClient productWebClient(WebClient.Builder builder) {
        String productUrl = System.getenv()
                .getOrDefault("PRODUCT_SERVICE_URL", "http://product-service:8081");
        return builder.baseUrl(productUrl).build();
    }
}
