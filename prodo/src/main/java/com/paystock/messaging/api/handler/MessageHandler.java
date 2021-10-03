package com.paystock.messaging.api.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Slf4j
@Component
public class MessageHandler {
    public Mono<ServerResponse> healthCheck(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(fromValue("hello "+ request.pathVariable("name")));
    }

    public Mono<ServerResponse> send(ServerRequest request){
        return request.bodyToMono(MessageDto.class)
                .doOnNext(msg -> log.info("User: {} -> : {}", msg.getUserId(), msg.getMessage()))
                .flatMap(message ->
                        ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(message)
                );
    }
}
