package com.finalproject.walletforex.controller;

import com.finalproject.walletforex.dao.TransactionDao;
import com.finalproject.walletforex.dto.CommonResponse;
import com.finalproject.walletforex.dto.TransactionDto;
import com.finalproject.walletforex.exception.AccountNotFoundException;
import com.finalproject.walletforex.exception.BalanceNotEnoughException;
import com.finalproject.walletforex.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionDao transactionDao;

    private static final String ADD_TRANSACTION = "/transaction"; //path Add Transaction
    private static final String LIST_TRANSACTION = "/account/{acc}/transactions"; //path Get List transaction

    @PostMapping(path = ADD_TRANSACTION)
    public CommonResponse<Transaction> add(@RequestBody TransactionDto dto) throws AccountNotFoundException, BalanceNotEnoughException {
        CommonResponse<Transaction> response = new CommonResponse<>();
        Transaction transaction = transactionDao.transaction(dto);
        response.setData(transaction);
        return response;
    }

    @GetMapping(path = LIST_TRANSACTION)
    public CommonResponse<List<Transaction>> unreg(@PathVariable(value = "acc") String acc) throws AccountNotFoundException {
        CommonResponse<List<Transaction>> response = new CommonResponse<>();
        List<Transaction> transactions = transactionDao.getList(acc);
        response.setData(transactions);
        return response;
    }
}
