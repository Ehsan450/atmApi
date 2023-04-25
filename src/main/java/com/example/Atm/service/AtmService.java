package com.example.Atm.service;

import com.example.Atm.entity.Account;
import com.example.Atm.entity.Card;
import com.example.Atm.entity.Transaction;
import com.example.Atm.enumator.TransactionType;
import com.example.Atm.repository.AccountRepository;
import com.example.Atm.repository.CardRepository;
import com.example.Atm.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class AtmService {
    private AccountRepository accountRepository;
    private CardRepository cardRepository;
    private TransactionRepository transactionRepository;

    public AtmService(AccountRepository accountRepository, CardRepository cardRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.cardRepository = cardRepository;
        this.transactionRepository = transactionRepository;
    }

    public boolean withdraw(String cardNo, String pin, double amount) {
        Optional<Card> card = cardRepository.findById(cardNo);

        if (card.isPresent()) {
            Card fetchedCard = card.get();
            if (fetchedCard.getPin().equals(pin)) {
                Account account = fetchedCard.getAccount();

                if (amount > 0 && amount % 500 == 0) {
                    if (amount <= account.getBalance()) {
                        account.setBalance(account.getBalance() - amount);
                        accountRepository.saveAndFlush(account);
                        System.out.println("Withdrawn: " + amount);
                        Transaction transaction = new Transaction();
                        transaction.setTrxType(TransactionType.WITHDRAW);
                        transaction.setSrcAccount(account);
                        transaction.setDestAccount(account);
                        transaction.setTransactionOn(LocalDateTime.now());
                        transactionRepository.saveAndFlush(transaction);


                    }
                    throw new CustomizedException("Insufficient Balance");


                }
                throw new CustomizedException("Amount must be in multiples of 500");

            }

            throw new CustomizedException("PIN Incorrect");

        }
        throw new CustomizedException("Card not found");


    }

    public boolean deposit(String cardNo, String pin, double amount) {
        Optional<Card> card = cardRepository.findById(cardNo);

        if (card.isPresent()) {
            Card fetchedCard = card.get();
            if (fetchedCard.getPin().equals(pin)) {

                Account account = fetchedCard.getAccount();
                if (amount > 0) {

                    account.setBalance(account.getBalance() + amount);
                    accountRepository.saveAndFlush(account);
                    System.out.println("Deposited Successfully : " + amount);
                    Transaction transaction = new Transaction();
                    transaction.setTrxType(TransactionType.DEPOSIT);
                    transaction.setSrcAccount(account);
                    transaction.setDestAccount(account);
                    transaction.setTransactionOn(LocalDateTime.now());
                    transactionRepository.saveAndFlush(transaction);

                    return true;
                }
                throw new CustomizedException("Input amount wrong");

            }
            throw new CustomizedException("Wrong Pin");

        }
        throw new CustomizedException("Card not Found");
    }
}






