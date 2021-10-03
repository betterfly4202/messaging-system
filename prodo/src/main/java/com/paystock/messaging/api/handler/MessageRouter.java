package com.paystock.messaging.api.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@EnableWebFlux
@Configuration
@RequiredArgsConstructor
public class MessageRouter {
    private static final String prefix= "/api";
    private final MessageHandler messageHandler;

    @Bean
    public RouterFunction<ServerResponse> healthCheckRoute(){
        return RouterFunctions.route(RequestPredicates.GET("/hello/{name}")
                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), messageHandler::healthCheck);
    }

    @Bean
    public RouterFunction<ServerResponse> sendRoute(){
        return RouterFunctions.route(RequestPredicates.POST(prefix+"/send")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), messageHandler::send);
    }
}
