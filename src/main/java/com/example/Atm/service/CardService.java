package com.example.Atm.service;

import com.example.Atm.entity.Card;
import com.example.Atm.repository.CardRepository;

import java.util.List;
import java.util.Optional;

public class CardService {
    private CardRepository cardRepository;

    public Optional<Card> findCard(String cardNo){
        return cardRepository.findById(cardNo);

    }
    public List<Card> findAllCards(){
        return cardRepository.findAll();
    }
    public List<Card> cardAllottedAccount(String accountNo){
        return  cardRepository.findAllByAccountNumber(accountNo);
    }


}
