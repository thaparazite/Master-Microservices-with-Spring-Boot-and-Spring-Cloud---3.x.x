package com.sketysoft.microservices.api_gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

   // create a router for the API gateway
   @Bean
   public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {

      return builder.routes()
                    .route(predicateSpec ->
                           predicateSpec.path("/get")
                                        .filters(filter ->
                                                 filter.addRequestHeader("MyHeader", "MyURI"))
                                        // hard coded the URI for the service
                                        .uri("http://httpbin.org:80"))
                    .route(predicateSpec ->
                           predicateSpec.path("/currency-exchange/**")
                                        // using loadbalancer look for the service name in Eureka server registry
                                        .uri("lb://currency-exchange-service"))
                    .route(predicateSpec ->
                           predicateSpec.path("/currency-conversion/**")
                                        // using loadbalancer look for the service name in Eureka server registry
                                        .uri("lb://currency-conversion-service"))
                    .route(predicateSpec ->
                           predicateSpec.path("/currency-conversion-openfeign/**")
                                        // using loadbalancer look for the service name in Eureka server registry
                                        .uri("lb://currency-conversion-service"))

                    // rewrite the path for the /currency-conversion-openfeign service
                    .route(predicateSpec ->
                           predicateSpec.path("/currency-conversion-openfeign-custom/**")
                                        .filters(filter ->
                                                 filter.rewritePath("/currency-conversion-openfeign-custom/(?<segment>.*)",
                                                                    "/currency-conversion-openfeign/${segment}"))
                                        // using loadbalancer look for the service name in Eureka server registry
                                        .uri("lb://currency-conversion-service"))
                    .build();
   }// end of gatewayRouter method

}// end of ApiGatewayConfiguration class
