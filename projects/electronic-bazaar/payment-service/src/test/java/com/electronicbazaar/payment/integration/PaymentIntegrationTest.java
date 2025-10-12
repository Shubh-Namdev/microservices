// package com.electronicbazaar.payment.integration;

// import com.electronicbazaar.payment.model.Payment;
// import com.electronicbazaar.payment.repository.PaymentRepository;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.web.client.TestRestTemplate;
// import org.springframework.http.*;

// import static org.assertj.core.api.Assertions.assertThat;

// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// class PaymentIntegrationTest {

//     @Autowired
//     private PaymentRepository paymentRepository;

//     @Autowired
//     private TestRestTemplate restTemplate;

//     private String baseUrl;
//     private String fakeJwtToken;

//     @BeforeEach
//     void setup() {
//         baseUrl = "http://localhost:8085/payments";
//         fakeJwtToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
//                 "eyJzdWIiOiJ0ZXN0QHVzZXIuY29tIiwiZXhwIjoxOTAwMDAwMDAwfQ." +
//                 "OeOygPOlSfsGx5b8zMfaPDa0GkvP5h5-YHuTo0tH8zQ"; // example token
//     }

//     @Test
//     void shouldCreatePaymentSuccessfully() {
//         Payment request = Payment.builder()
//                 .orderId(1L)
//                 .amount(199.99)
//                 .build();

//         HttpHeaders headers = new HttpHeaders();
//         headers.set("Authorization", fakeJwtToken);
//         headers.setContentType(MediaType.APPLICATION_JSON);

//         HttpEntity<Payment> entity = new HttpEntity<>(request, headers);

//         ResponseEntity<Payment> response =
//                 restTemplate.exchange(baseUrl, HttpMethod.POST, entity, Payment.class);

//         assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//         assertThat(response.getBody()).isNotNull();
//         assertThat(response.getBody().getTransactionId()).isNotBlank();

//         // Verify persisted data
//         Payment saved = paymentRepository.findById(response.getBody().getId()).orElseThrow();
//         assertThat(saved.getAmount()).isEqualTo(199.99);
//     }
// }
