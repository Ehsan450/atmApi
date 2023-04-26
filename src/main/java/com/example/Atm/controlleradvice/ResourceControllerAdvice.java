package com.example.Atm.controlleradvice;

import com.example.Atm.exception.CardNotFoundException;
import com.example.Atm.exception.CustomizedException;
import com.example.Atm.exception.GlobalErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = {"com.example.Atm.controller"})
public class ResourceControllerAdvice {
    private final HttpServletRequest httpServletRequest;

    @Autowired
    public ResourceControllerAdvice(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<GlobalErrorResponse> handleCardNotFoundException(CardNotFoundException exception) {
        GlobalErrorResponse errorResponse = new GlobalErrorResponse(HttpStatus.BAD_REQUEST,
                exception.getMessage(), httpServletRequest.getRequestURI());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomizedException.class)
    public ResponseEntity<GlobalErrorResponse> handleCustomizedException(CustomizedException exception) {
        GlobalErrorResponse errorResponse = new GlobalErrorResponse(HttpStatus.BAD_REQUEST,
                exception.getMessage(), httpServletRequest.getRequestURI());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
