package com.example.Atm.controller;

import com.example.Atm.entity.Card;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class AtmController {
    @GetMapping("/deposit")
    public ResponseEntity<?> depositBalance(@ModelAttribute Card card) {
        // atmService.deposit(actions.getCardNo(), actions.getPin(), actions.getAmount());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT,HttpStatus.OK);
    }

}
