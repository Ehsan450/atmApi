package com.example.Atm.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "account_no")
    private String accountNumber;

    @Column(name = "account_name")
    private String accountName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "balance")
    private double balance;
}
