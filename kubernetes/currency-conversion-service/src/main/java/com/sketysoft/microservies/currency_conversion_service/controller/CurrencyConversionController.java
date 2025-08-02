package com.sketysoft.microservies.currency_conversion_service.controller;

import com.sketysoft.microservies.currency_conversion_service.CurrencyConversion;
import com.sketysoft.microservies.currency_conversion_service.CurrencyExchangeProxy;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Objects;

@RestController
@AllArgsConstructor
public class CurrencyConversionController {

   private final Logger logger = LoggerFactory.getLogger(CurrencyConversionController.class);
   private CurrencyExchangeProxy currencyExchangeProxy;
   private RestTemplate restTemplate;

   @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
   public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to,
                                                         @PathVariable BigDecimal quantity) {
      //CHANGE-KUBERNETES
      logger.info("calculateCurrencyConversion called with {} to {} with {}", from, to, quantity);

      HashMap<String, String> uriVariables = new HashMap<>();
      uriVariables.put("from", from);
      uriVariables.put("to", to);


      /*
       * make use of the restTemplate variable to call the currency exchange service
       * and this way we are activating the tracing of the calls to the exchange service
       */
      ResponseEntity<CurrencyConversion> responseEntity =
      restTemplate.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                                CurrencyConversion.class, uriVariables);

      CurrencyConversion conversion = responseEntity.getBody();

      if (conversion == null) {
         logger.error("Currency conversion data is null for {} to {}", from, to);
         throw new RuntimeException("Currency conversion data not found");
      }


      return new CurrencyConversion(Objects.requireNonNull(conversion).getId(), from, to, quantity,
                                    conversion.getConversionMultiple(), quantity.multiply(conversion.getConversionMultiple()),
                                    conversion.getEnvironment() + " : rest template");
   }// end of calculateCurrencyConversion

   @GetMapping("/currency-conversion-openfeign/from/{from}/to/{to}/quantity/{quantity}")
   public CurrencyConversion calculateCurrencyConversionOpenfeign(@PathVariable String from, @PathVariable String to,
                                                                  @PathVariable BigDecimal quantity) {

      //CHANGE-KUBERNETES
      logger.info("calculateCurrencyConversionFeign called with {} to {} with {}", from, to, quantity);

      CurrencyConversion conversion = currencyExchangeProxy.getExchangeRate(from, to);

      return new CurrencyConversion(Objects.requireNonNull(conversion).getId(), from, to, quantity,
                                    conversion.getConversionMultiple(), quantity.multiply(conversion.getConversionMultiple()),
                                    conversion.getEnvironment() + " : openfeign");
   }// end of calculateCurrencyConversion

   @GetMapping("/conversion")
   public String test() {
      logger.info("conversion-test-method-called");
      return "CONVERSION SERVICE";
   }// end of test

}// end of class CurrencyConversionController
