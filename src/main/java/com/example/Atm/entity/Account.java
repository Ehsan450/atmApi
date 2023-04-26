package com.example.Atm.entity;

import com.example.Atm.exception.CustomizedException;
import com.example.Atm.service.Utils;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @Setter(AccessLevel.NONE)
    @Column(name = "account_no")
    private String accountNumber;

    @Column(name = "account_name")
    private String accountName;

    @Setter(AccessLevel.NONE)
    @Column(name = "balance")
    private double balance;

    @PrePersist
    private void init() {
        this.accountNumber = Utils.generate(14);
        this.balance = 50000;
    }

    public void withdraw(double amount) {
        if (amount >= this.balance) {
            if (amount > 0 && amount % 500 == 0) {
                this.balance = this.balance - amount;
            } else {
                throw new CustomizedException("Amount must be in multiples of 500");
            }
        } else {
            throw new CustomizedException("Amount exceeds current available balance");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance = this.balance + amount;
        } else {
            throw new CustomizedException("Invalid amount");
        }
    }
}
