package com.CodeCluster.ApiGateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtValidationGatewayFilterFactory extends
        AbstractGatewayFilterFactory<JwtValidationGatewayFilterFactory.Configs> {

    private static final Logger log = LoggerFactory.getLogger(JwtValidationGatewayFilterFactory.class);
    private final WebClient.Builder webClientBuilder;


    public JwtValidationGatewayFilterFactory(WebClient.Builder webClientBuilder){
        super(Configs.class);
        this.webClientBuilder = webClientBuilder;
    }

    ///
    public GatewayFilter apply(Configs configs){
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

                if(authHeader == null || authHeader.isEmpty() || !authHeader.startsWith("Bearer ")){
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

                    return exchange.getResponse().setComplete();
                }

                return webClientBuilder.build()
                        .get()
                        .uri("http://localhost:3030/validate")
                        .header(HttpHeaders.AUTHORIZATION, authHeader)
                        .retrieve()
                        .toBodilessEntity()
                        .flatMap(response -> {
                            return chain.filter(exchange);
                        })
                        .onErrorResume(error -> {
                            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                            return exchange.getResponse().setComplete();
                        });



            }
        };


    }


    public static class Configs{

    }
}
