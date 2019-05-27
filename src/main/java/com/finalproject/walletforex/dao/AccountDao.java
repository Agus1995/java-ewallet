package com.finalproject.walletforex.dao;

import com.finalproject.walletforex.dto.AccountDto;
import com.finalproject.walletforex.exception.AccountNotFoundException;
import com.finalproject.walletforex.model.Account;

public interface AccountDao {
    Account addAccount(AccountDto dto);
    Account findByCif(String cif);
    Account updateBalance(Account account) throws AccountNotFoundException;
}
