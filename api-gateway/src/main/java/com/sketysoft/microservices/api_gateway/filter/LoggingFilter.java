package com.sketysoft.microservices.api_gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter {

   private final Logger logger = LoggerFactory.getLogger(this.getClass());

   @Override
   public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
      // log the request path
      logger.info("Path of the request received -> {}", exchange.getRequest().getPath());

      // return the gateway  filter chain
      return chain.filter(exchange);
   }// end of filter method

}// end of LoggingFilter class
