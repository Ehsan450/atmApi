package com.example.Atm.exception;

import lombok.Getter;

import java.io.Serial;

@Getter
public class CustomizedException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 8560383641010432699L;

    private final String message;

    public CustomizedException(String s){
        super(s);
        this.message = s;
    }
}
