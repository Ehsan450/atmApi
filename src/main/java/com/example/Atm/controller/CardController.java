package com.example.Atm.controller;

import com.example.Atm.entity.Card;
import com.example.Atm.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cards", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/")
    public List<Card> findAllCard() {
        return cardService.findAllCards();
    }
}
