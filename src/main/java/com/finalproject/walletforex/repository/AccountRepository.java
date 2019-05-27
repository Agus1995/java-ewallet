package com.finalproject.walletforex.repository;

import com.finalproject.walletforex.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
