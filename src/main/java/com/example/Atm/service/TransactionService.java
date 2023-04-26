package com.example.Atm.service;

import com.example.Atm.entity.Transaction;
import com.example.Atm.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
 private final TransactionRepository transactionRepository;
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> findAllTransactionsForAccount(String accountNo){
        return transactionRepository.findAllBySrcAccountAccountNumber(accountNo);
    }
}
