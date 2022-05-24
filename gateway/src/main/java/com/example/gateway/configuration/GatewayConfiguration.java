package com.example.gateway.configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public RouteLocator getRoutLocator(RouteLocatorBuilder builder){
        return builder
                .routes()
                .route(predicateSpec -> predicateSpec.path("/school/**").uri("lb://free-access"))
                .build();
    }

}
