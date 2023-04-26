package com.example.Atm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "cards")
public class Card {
    @Id
    @Column(name = "card_no")
    private String cardNo;

    @ManyToOne
    @JoinColumn(name = "account_no", referencedColumnName = "account_no")
    private Account account;

    @Column(name = "pin")
    private String pin;
}