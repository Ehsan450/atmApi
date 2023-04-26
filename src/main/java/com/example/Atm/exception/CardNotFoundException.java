package com.example.Atm.exception;

import lombok.Getter;

import java.io.Serial;

@Getter
public class CardNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1249262851432961323L;

    private final String message;

    public CardNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
