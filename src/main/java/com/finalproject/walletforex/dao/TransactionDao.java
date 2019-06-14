package com.finalproject.walletforex.dao;

import com.finalproject.walletforex.dto.TransactionDto;
import com.finalproject.walletforex.exception.AccountNotFoundException;
import com.finalproject.walletforex.exception.BalanceNotEnoughException;
import com.finalproject.walletforex.model.Transaction;

import java.util.List;

public interface TransactionDao {
    Transaction transaction(TransactionDto dto) throws AccountNotFoundException, BalanceNotEnoughException;
    List<Transaction> getList(String accNumber);
}
