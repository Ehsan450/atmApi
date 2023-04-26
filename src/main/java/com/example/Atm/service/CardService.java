package com.example.Atm.service;

import com.example.Atm.entity.Card;
import com.example.Atm.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    public CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> findAllCards(){
        return cardRepository.findAll();
    }
}
