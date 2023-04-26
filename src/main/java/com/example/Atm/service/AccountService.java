package com.example.Atm.service;

import com.example.Atm.entity.Account;
import com.example.Atm.entity.Card;
import com.example.Atm.repository.AccountRepository;
import com.example.Atm.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AccountService {
    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, CardRepository cardRepository) {
        this.accountRepository = accountRepository;
        this.cardRepository = cardRepository;
    }

    public Account createAccount(Account account) {
        Account savedAccount = new Account();
        savedAccount.setAccountName(account.getAccountName());
        this.accountRepository.save(savedAccount);

        Card generatedCard = new Card();
        generatedCard.setCardNo(Utils.generate(12));
        generatedCard.setPin(Utils.generate(4));
        generatedCard.setAccount(savedAccount);
        this.cardRepository.saveAndFlush(generatedCard);

        return savedAccount;
    }

    public List<Account> findAllAccount() {
        return this.accountRepository.findAll();
    }
}
