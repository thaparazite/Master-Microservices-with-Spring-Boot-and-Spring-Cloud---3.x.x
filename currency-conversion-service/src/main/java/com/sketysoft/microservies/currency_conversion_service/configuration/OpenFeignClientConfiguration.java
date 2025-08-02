package com.sketysoft.microservies.currency_conversion_service.configuration;

import feign.Logger;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenFeignClientConfiguration {

   @Bean
   Logger.Level feignLoggerLevel() {
      return Logger.Level.FULL;
   }

   @Bean
   public ObservationRegistry observationRegistry() {
      return ObservationRegistry.create();
   }

}// end of OpenFeignClientConfiguration class
