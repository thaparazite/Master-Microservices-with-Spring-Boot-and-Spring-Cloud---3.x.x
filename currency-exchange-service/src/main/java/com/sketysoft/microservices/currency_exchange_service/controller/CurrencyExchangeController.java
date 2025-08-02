package com.sketysoft.microservices.currency_exchange_service.controller;

import com.sketysoft.microservices.currency_exchange_service.CurrencyExchange;
import com.sketysoft.microservices.currency_exchange_service.CurrencyExchangeRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
/*
 *  the parameter is used for constructor injection otherwise
 *  Spring will use field injection or setter injection
 */
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CurrencyExchangeController {

   private static final Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

   private final Environment environment;
   private final CurrencyExchangeRepository repository;

   @GetMapping("/currency-exchange/from/{from}/to/{to}")
   public CurrencyExchange getExchangeRate(@PathVariable String from, @PathVariable String to) {

      logger.info("getExchangeRate called with from: {} and to: {}", from, to);

      CurrencyExchange exchange = repository.findByFromAndTo(from, to);
      if (exchange == null) {
         throw new RuntimeException("Exchange rate not found for " + from + " to " + to);
      }
      String port = environment.getProperty("local.server.port");
      exchange.setEnvironment(port);

      return exchange;
   }// end of getExchangeRate

}// end of class CurrencyExchangeController
