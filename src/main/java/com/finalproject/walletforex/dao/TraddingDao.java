package com.finalproject.walletforex.dao;

import com.finalproject.walletforex.exception.AccountNotFoundException;
import com.finalproject.walletforex.exception.BalanceNotEnoughException;
import com.finalproject.walletforex.model.Account;
import com.finalproject.walletforex.model.ForexTradding;

public interface TraddingDao {
    ForexTradding buy(ForexTradding forexTradding, Account account) throws AccountNotFoundException, BalanceNotEnoughException;
    ForexTradding sell(ForexTradding forexTradding, Account account);
}
