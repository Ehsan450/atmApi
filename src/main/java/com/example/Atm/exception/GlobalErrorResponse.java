package com.example.Atm.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
public class GlobalErrorResponse {
    private final int status;
    private final String error;
    private final String message;
    private final String path;
    private final long timestamp;

    public GlobalErrorResponse(HttpStatus httpStatus, String message, String path) {
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.message = message;
        this.path = path;
        this.timestamp = Instant.now().getEpochSecond();
    }
}
