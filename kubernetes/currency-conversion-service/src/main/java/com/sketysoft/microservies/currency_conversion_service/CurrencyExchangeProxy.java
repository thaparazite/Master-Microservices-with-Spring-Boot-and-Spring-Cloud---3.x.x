package com.sketysoft.microservies.currency_conversion_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange-service", url = "http://localhost:8000")
//@FeignClient(name = "currency-exchange", url = "http://localhost:8000")

// CHANGE-KUBERNETES
/*
 * because we created a service called currency-exchange-service
 * Kubernetes will automatically create and set the variable
 * CURRENCY_EXCHANGE and add _SERVICE_HOST to it
 * so we can use it in the url
 * CURRENCY_EXCHANGE_SERVICE_HOST
 *
 */
//@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_SERVICE_HOST:http://localhost}:8000")
@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_URI:http://localhost}:8000")
public interface CurrencyExchangeProxy {

   @GetMapping("/currency-exchange/from/{from}/to/{to}")
   CurrencyConversion getExchangeRate(@PathVariable String from, @PathVariable String to);

}// end of class CurrencyExchangeProxy
