package com.example.Atm.repository;

import com.example.Atm.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, String> {
    List<Card> findAllByAccountAccountNumber(String accountNumber);
}
