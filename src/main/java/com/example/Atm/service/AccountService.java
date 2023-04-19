package com.example.Atm.service;

import com.example.Atm.entity.Account;
import com.example.Atm.entity.Card;
import com.example.Atm.repository.AccountRepository;
import com.example.Atm.repository.CardRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Setter
@Getter
public class AccountService {
    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;


    public AccountService(AccountRepository accountRepository, CardRepository cardRepository) {
        this.accountRepository = accountRepository;
        this.cardRepository = cardRepository;
    }

    public Optional<Account> findAccount(String id) {
        return accountRepository.findById(id);
    }

    public Account createAccount(String accountName) {
        Account savedAccount = new Account();
        savedAccount.setAccountName(accountName);
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
    public void deleteAccount(String id){
        accountRepository.deleteById(id);

    }
}
