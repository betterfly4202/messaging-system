package com.paystock.messaging.api.handler;

import com.paystock.messaging.api.dto.MessageDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MessageRouterTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void health_check(){
        // given
        String uri = "/hello/betterfly";

        // when & then
        webTestClient.get().uri(uri)
                .accept(MediaType.TEXT_PLAIN)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("hello betterfly");
    }

    @Test
    public void 유저정보를_포함해서_메시지가_전송된다(){
        // given
        String uri = "/api/send";
        MessageDto messageDto = new MessageDto("betterFLY", "Hello");

        // when & then
        webTestClient.post().uri(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(messageDto), MessageDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .expectBody()
                .jsonPath("$.status").isEqualTo(200)
                .jsonPath("$.data.userId").isEqualTo("betterFLY")
                .jsonPath("$.data.message").isEqualTo("Hello");
    }

    @Test
    public void 메시지에_공백을_입력할_경우_예외를_발생시킨다(){
        // given
        String uri = "/api/send";
        MessageDto messageDto = new MessageDto("betterFLY", "");

        // when & then
        webTestClient.post().uri(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(messageDto), MessageDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .expectBody()
                .jsonPath("$.status").isEqualTo(400);
    }
}