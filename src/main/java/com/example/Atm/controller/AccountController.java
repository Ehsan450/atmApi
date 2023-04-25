package com.example.Atm.controller;

import com.example.Atm.entity.Account;
import com.example.Atm.entity.Transaction;
import com.example.Atm.service.AccountService;
import com.example.Atm.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountController {
    private final AccountService accountService;
    private final TransactionService transactionService;

    @Autowired
    public AccountController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllAccount() {
        List<Account> accounts = this.accountService.findAllAccount();

        return accounts.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> saveAccount(@RequestBody Account account) {
        return new ResponseEntity<>(this.accountService.createAccount(account), HttpStatus.CREATED);
    }

    @GetMapping("/transactions/{accountNumber}")
    public ResponseEntity<?> getTransactions(@PathVariable("accountNumber") String accountNumber) {
        List<Transaction> transactions = this.transactionService.findAllTransactionsForAccount(accountNumber);

        return transactions.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
