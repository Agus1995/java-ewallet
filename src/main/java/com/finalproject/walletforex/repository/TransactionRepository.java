package com.finalproject.walletforex.repository;

import com.finalproject.walletforex.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByAccCreditOrAccDebet(String debet, String credit);
}
