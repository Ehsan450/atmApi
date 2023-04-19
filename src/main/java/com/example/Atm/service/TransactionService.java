package com.example.Atm.service;

import com.example.Atm.entity.Transaction;
import com.example.Atm.repository.AccountRepository;
import com.example.Atm.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;

public class TransactionService {
 private TransactionRepository transactionRepository;
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    private Optional<Transaction> findTransaction(int id) {
        return transactionRepository.findById(id);
    }

    private List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }
}
