package com.finalproject.walletforex.dao;

import com.finalproject.walletforex.dto.WalletDto;
import com.finalproject.walletforex.model.Wallet;

import java.util.List;

public interface WalletDao {
    Wallet addWallet(WalletDto dto);
    List<Wallet> findByCif(String cif);
}
