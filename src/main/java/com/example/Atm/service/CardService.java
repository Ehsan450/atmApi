package com.example.Atm.service;

import com.example.Atm.entity.Card;
import com.example.Atm.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class CardService {
    public CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Optional<Card> findCard(String cardNo){
        return cardRepository.findById(cardNo);

    }

    public List<Card> findAllCards(){
        return cardRepository.findAll();
    }

    public List<Card> cardAllottedAccount(String accountNo){
        return cardRepository.findAllByAccountAccountNumber(accountNo);
    }
}
