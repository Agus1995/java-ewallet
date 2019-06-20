package com.finalproject.walletforex.dao;

import com.finalproject.walletforex.dto.TraddingDto;
import com.finalproject.walletforex.exception.AccountNotFoundException;
import com.finalproject.walletforex.exception.BalanceNotEnoughException;
import com.finalproject.walletforex.exception.WalletNotFoundException;
import com.finalproject.walletforex.model.ForexTradding;
import com.finalproject.walletforex.model.TraddingReport;

import java.util.List;

public interface TraddingDao {
    ForexTradding buy(TraddingDto traddingDto) throws AccountNotFoundException, BalanceNotEnoughException;
    ForexTradding sell(TraddingDto traddingDto) throws BalanceNotEnoughException, AccountNotFoundException;
    List<ForexTradding> getByCif(String cif) throws WalletNotFoundException;
    List<ForexTradding> getWithFif(String cif) throws AccountNotFoundException;
    double checksum(String cif);
    List<TraddingReport> getReport(String cif);
}
