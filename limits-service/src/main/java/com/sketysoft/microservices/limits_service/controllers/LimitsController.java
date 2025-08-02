package com.sketysoft.microservices.limits_service.controllers;

import com.sketysoft.microservices.limits_service.bean.Limits;
import com.sketysoft.microservices.limits_service.configuration.Configuration;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LimitsController {

   private Configuration config;

   @GetMapping("/limits")
   public Limits retrieveLimits() {
      return new Limits(config.getMinimum(), config.getMaximum());
   }// end of retrieveLimits method

}// end of class LimitsController
