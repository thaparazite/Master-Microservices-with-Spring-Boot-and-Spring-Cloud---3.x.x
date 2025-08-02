package com.sketysoft.microservies.currency_conversion_service.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// this class is used to create a RestTemplate bean and trace the calls to the exchange service
@Configuration(proxyBeanMethods = false)
public class RestTemplateConfiguration {

   @Bean
   // @LoadBalanced // this annotation is used to enable load balancing for the
   // RestTemplate
   RestTemplate restTemplate(RestTemplateBuilder builder) {
      return builder.build();
   }// end of restTemplate method

}// end of RestTemplateConfiguration class
