package com.example.Atm.service;

import com.example.Atm.entity.Account;
import com.example.Atm.entity.Card;
import com.example.Atm.repository.AccountRepository;
import com.example.Atm.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class AccountService {
    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, CardRepository cardRepository) {
        this.accountRepository = accountRepository;
        this.cardRepository = cardRepository;
    }

    public Optional<Account> findAccount(String id) {
        return accountRepository.findById(id);
    }

    public Account createAccount(Account account) {
        Account savedAccount = new Account();
        savedAccount.setAccountName(account.getAccountName());
        savedAccount.setAccountNumber(Utils.generate(14));
        savedAccount.setBalance(50000.78);
        accountRepository.save(savedAccount);
        Card generatedCard = new Card();
        generatedCard.setCardNo(Utils.generate(12));
        generatedCard.setPin(Utils.generate(4));
        generatedCard.setAccount(savedAccount);
        cardRepository.saveAndFlush(generatedCard);
        return savedAccount;
    }

    public void deleteAccount(String id) {
        accountRepository.deleteById(id);

    }

    public List<Account> findAllAccount() {
        return accountRepository.findAll();
    }
}
