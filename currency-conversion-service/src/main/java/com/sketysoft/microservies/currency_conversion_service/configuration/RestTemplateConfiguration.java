package com.sketysoft.microservies.currency_conversion_service.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// this class is used to create a RestTemplate bean and trace the calls to the exchange service
@Configuration
public class RestTemplateConfiguration {

   @Bean
   RestTemplate restTemplate(RestTemplateBuilder builder) {
      return builder.build();
   }// end of restTemplate method

}// end of RestTemplateConfiguration class
