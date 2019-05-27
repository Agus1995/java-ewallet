package com.finalproject.walletforex.repository;

import com.finalproject.walletforex.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {
    Wallet findByCustomer_Cif(String cif);
}
