package com.example.Atm.controller;

import com.example.Atm.entity.Account;
import com.example.Atm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/account")
    public List<Account> getAllAccount() {
        return accountService.findAllAccount();
    }
}
