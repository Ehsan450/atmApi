package com.example.Atm.repository;

import com.example.Atm.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, String> {
    List<Card> findAllByAccountAccountNumber(String accountNumber);
}
