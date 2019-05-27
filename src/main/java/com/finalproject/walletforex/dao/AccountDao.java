package com.finalproject.walletforex.dao;

import com.finalproject.walletforex.dto.AccountDto;
import com.finalproject.walletforex.exception.AccountNotFoundException;
import com.finalproject.walletforex.model.Account;

import java.util.List;

public interface AccountDao {
    Account addAccount(AccountDto dto);
    List<Account> findByCif(String cif);
    Account updateBalance(Account account) throws AccountNotFoundException;
    Account findById(String id) throws AccountNotFoundException;
}
