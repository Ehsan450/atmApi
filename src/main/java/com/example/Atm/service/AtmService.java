package com.example.Atm.service;

import com.example.Atm.entity.Account;
import com.example.Atm.entity.Card;
import com.example.Atm.entity.Transaction;
import com.example.Atm.enumator.TransactionType;
import com.example.Atm.exception.CardNotFoundException;
import com.example.Atm.exception.CustomizedException;
import com.example.Atm.repository.AccountRepository;
import com.example.Atm.repository.CardRepository;
import com.example.Atm.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtmService {
    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public AtmService(AccountRepository accountRepository, CardRepository cardRepository,
                      TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.cardRepository = cardRepository;
        this.transactionRepository = transactionRepository;
    }

    public boolean withdraw(String cardNo, String pin, double amount) {
        Optional<Card> card = this.cardRepository.findById(cardNo);

        if (card.isPresent()) {
            Card fetchedCard = card.get();
            if (fetchedCard.getPin().equals(pin)) {
                Account account = fetchedCard.getAccount();
                account.withdraw(amount);
                this.accountRepository.saveAndFlush(account);

                Transaction transaction = new Transaction();
                transaction.setTrxType(TransactionType.WITHDRAW);
                transaction.setSrcAccount(account);
                transaction.setDestAccount(account);
                this.transactionRepository.saveAndFlush(transaction);

                return true;

            }
            throw new CustomizedException("PIN Incorrect");
        }
        throw new CardNotFoundException("Card not found");

    }

    public boolean deposit(String cardNo, String pin, double amount) {
        Optional<Card> card = cardRepository.findById(cardNo);

        if (card.isPresent()) {
            Card fetchedCard = card.get();
            if (fetchedCard.getPin().equals(pin)) {
                Account account = fetchedCard.getAccount();
                account.deposit(amount);
                this.accountRepository.saveAndFlush(account);
                Transaction transaction = new Transaction();
                transaction.setTrxType(TransactionType.DEPOSIT);
                transaction.setSrcAccount(account);
                transaction.setDestAccount(account);
                this.transactionRepository.saveAndFlush(transaction);

                return true;
            }
            throw new CustomizedException("Wrong Pin");
        }
        throw new CardNotFoundException("Card not Found");
    }


    public boolean transfer(RequiredFields requiredFields) {
        Optional<Card> card = cardRepository.findById(requiredFields.getCardNo());
        if (card.isPresent()) {
            Card fetchedCard = card.get();
            if (fetchedCard.getPin().equals(requiredFields.getPin())) {
                Account srcAccount = fetchedCard.getAccount();
                if (requiredFields.getAmount() > 0) {
                    if (srcAccount.getBalance() >= requiredFields.getAmount()) {
                        if (fetchedCard.getAccount().getAccountNumber().equals(requiredFields.getSrcAccount())) {
                            Optional<Account> destinationAccount = this.accountRepository.findById(requiredFields.getDestAccount());
                            if (destinationAccount.isPresent()) {
                                Account destAccount = destinationAccount.get();
                                srcAccount.withdraw(requiredFields.getAmount());
                                this.accountRepository.saveAndFlush(srcAccount);

                                Transaction transaction = new Transaction();
                                transaction.setTrxType(TransactionType.TRANSFER);
                                transaction.setSrcAccount(srcAccount);
                                transaction.setDestAccount(destAccount);
                                this.transactionRepository.saveAndFlush(transaction);

                                destAccount.deposit(requiredFields.getAmount());
                                this.accountRepository.saveAndFlush(destAccount);

                                return true;
                            }
                            throw new CustomizedException("Destination account not found");
                        }
                        throw new CustomizedException("Source account is invalid");
                    }
                    throw new CustomizedException("Amount exceeds current available balance");
                }
                throw new CustomizedException("Amount can't be negative");
            }
            throw new CustomizedException("Incorrect PIN");
        }
        throw new CardNotFoundException("Card Not Found");
    }
}



