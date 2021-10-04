package com.paystock.messaging.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Slf4j
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseEntity<T> {
    private final int status;
    private final T data;

    private ResponseEntity(@NonNull int status, @Nullable T data) {
        this.status = status;
        this.data = data;
    }

    public static <T> ResponseEntity<T> ok(@Nullable T data) {
        return new ResponseEntity(HttpStatus.OK.value(), data);
    }

    public static <T> ResponseEntity<T> fail(@Nullable T data) {
        log.error(data.toString());
        return new ResponseEntity(HttpStatus.BAD_REQUEST.value(), data);
    }
}
