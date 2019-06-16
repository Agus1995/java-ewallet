package com.finalproject.walletforex.dao;

import com.finalproject.walletforex.dto.TraddingDto;
import com.finalproject.walletforex.exception.AccountNotFoundException;
import com.finalproject.walletforex.exception.BalanceNotEnoughException;
import com.finalproject.walletforex.exception.WalletNotFoundException;
import com.finalproject.walletforex.model.ForexTradding;

import java.util.List;

public interface TraddingDao {
    ForexTradding buy(TraddingDto traddingDto) throws AccountNotFoundException, BalanceNotEnoughException;
    ForexTradding sell(TraddingDto traddingDto) throws BalanceNotEnoughException;
    List<ForexTradding> getByCif(String cif) throws WalletNotFoundException;
    double checksum(String cif);
}
