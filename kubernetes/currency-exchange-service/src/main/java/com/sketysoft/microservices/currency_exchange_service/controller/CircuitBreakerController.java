package com.sketysoft.microservices.currency_exchange_service.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircuitBreakerController {

   private final Logger log = LoggerFactory.getLogger(CircuitBreakerController.class);

   @GetMapping("/sample-api")
  // @Retry(name = "sample-api", fallbackMethod = "sampleAPIFallback")

   // use circuit breaker instead of retry to handle the failure
   // @CircuitBreaker(name = "default", fallbackMethod = "sampleAPIFallback")
   // @RateLimiter(name = "default")
   @Bulkhead(name = "sample-api")
   public String sampleAPI() {
      log.info("Sample API call received");
     /* ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8000/sample-api/", String.class);

      return forEntity.getBody();*/

      return "Sample API";

   }// end of sampleAPI method

   public String sampleAPIFallback(Exception e) {
      return "fallback-response";
   }// end of sampleAPIFallback method

}// end of class CircuitBreakerController
