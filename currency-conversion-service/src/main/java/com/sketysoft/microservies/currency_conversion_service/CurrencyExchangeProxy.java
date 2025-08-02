package com.sketysoft.microservies.currency_conversion_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange-service", url = "http://localhost:8000")
@FeignClient(name = "currency-exchange-service")
public interface CurrencyExchangeProxy {

   @GetMapping("/currency-exchange/from/{from}/to/{to}")
   CurrencyConversion getExchangeRate(@PathVariable String from, @PathVariable String to);

}// end of class CurrencyExchangeProxy
