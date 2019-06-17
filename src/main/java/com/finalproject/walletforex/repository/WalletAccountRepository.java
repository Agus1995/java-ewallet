package com.finalproject.walletforex.repository;

import com.finalproject.walletforex.model.Customer;
import com.finalproject.walletforex.model.WalletAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletAccountRepository extends JpaRepository<WalletAccount, Integer> {
    List<WalletAccount> findByAccountCustomer(Customer customer);
    List<WalletAccount> findByWallet_WalletId(String id);

}
