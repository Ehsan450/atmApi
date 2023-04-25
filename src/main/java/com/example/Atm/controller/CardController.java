package com.example.Atm.controller;

import com.example.Atm.entity.Card;
import com.example.Atm.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class CardController {
    private final CardService cardService;
    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/cards")
    public List<Card>  findAllCard(){
        return cardService.findAllCards();
    }
}
