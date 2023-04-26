package com.example.Atm.entity;

import com.example.Atm.enumator.TransactionType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Setter(AccessLevel.NONE)
    @Column(name = "transaction_on")
    private LocalDateTime transactionOn;

    @Enumerated(EnumType.STRING)
    @Column(name = "trx_type")
    private TransactionType trxType;

    @ManyToOne
    @JoinColumn(name = "src_account", referencedColumnName = "account_no")
    private Account srcAccount;

    @ManyToOne
    @JoinColumn(name = "dest_account", referencedColumnName = "account_no")
    private Account destAccount;

    @PrePersist
    private void init() {
        this.transactionOn = LocalDateTime.now();
    }
}
