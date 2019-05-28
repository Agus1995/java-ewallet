package com.finalproject.walletforex.repository;

import com.finalproject.walletforex.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    List<Account> findByCustomer_Cif(String cif);

}
