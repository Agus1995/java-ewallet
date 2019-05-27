package com.finalproject.walletforex.repository;

import com.finalproject.walletforex.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {
    List<Wallet> findByCustomer_Cif(String cif);
}
