package com.paystock.messaging.sender.service;

import com.paystock.messaging.api.dto.MessageDto;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@EnableAsync
public class SendMessageService {
    private WebClient webClient;

    @Value("${client.base-url:http://localhost}")
    private String BASE_URL;
    @Value("${client.port:8080}")
    private int PORT;
    @Value("${client.api.message-send}")
    private String SEND_URI;

    @Setter
    private String userId;

    @PostConstruct
    public void init(){
        this.webClient = WebClient.builder().
                baseUrl(BASE_URL+":"+PORT)
                .build();
    }

    @Async("asyncExecutor")
    public void send(String message) {
        requestClient(message).subscribe(v ->{
            log.warn("success : {}", v);
        });
    }

    private Mono<String> requestClient(String message) {
        return this.webClient.post()
                .uri(SEND_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new MessageDto(this.userId, message))
                .retrieve()
                .bodyToMono(String.class);
    }
}
