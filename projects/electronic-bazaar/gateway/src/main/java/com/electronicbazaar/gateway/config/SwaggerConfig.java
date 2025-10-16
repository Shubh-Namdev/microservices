package com.electronicbazaar.gateway.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi authApi() {
        return GroupedOpenApi.builder()
                .group("auth-service")
                .pathsToMatch("/auth/**")
                .build();
    }

    @Bean
    public GroupedOpenApi productApi() {
        return GroupedOpenApi.builder()
                .group("product-service")
                .pathsToMatch("/products/**")
                .build();
    }

    @Bean
    public GroupedOpenApi customerApi() {
        return GroupedOpenApi.builder()
                .group("customer-service")
                .pathsToMatch("/customers/**")
                .build();
    }

    @Bean
    public GroupedOpenApi orderApi() {
        return GroupedOpenApi.builder()
                .group("order-service")
                .pathsToMatch("/orders/**")
                .build();
    }

    @Bean
    public GroupedOpenApi paymentApi() {
        return GroupedOpenApi.builder()
                .group("payment-service")
                .pathsToMatch("/payments/**")
                .build();
    }
}