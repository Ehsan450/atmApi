package com.example.Atm.service;

import com.example.Atm.entity.Account;
import com.example.Atm.entity.Card;
import com.example.Atm.entity.Transaction;
import com.example.Atm.enumator.TransactionType;
import com.example.Atm.repository.AccountRepository;
import com.example.Atm.repository.CardRepository;
import com.example.Atm.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public class AtmService {
    private AccountRepository accountRepository;
    private CardRepository cardRepository;
    private TransactionRepository transactionRepository;

    public void withdraw(String cardNo,String pin,double amount){
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
                    throw new RuntimeException("Insufficient Balance");


                }
                throw new RuntimeException("Amount must be in multiples of 500");

            }

            throw new RuntimeException("PIN Incorrect");

        }
        throw new RuntimeException("Card not found");


    }
    public void deposit(String cardNo,String pin,double amount){
        Optional <Card> card= cardRepository.findById(cardNo);

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
                    }
                    System.out.println("Input amount wrong");


                }
                System.out.println("Wrong Pin");

            }
            System.out.println("Card not Found");
        }


        }






