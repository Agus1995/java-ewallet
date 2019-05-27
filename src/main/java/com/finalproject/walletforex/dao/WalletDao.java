package com.finalproject.walletforex.dao;

import com.finalproject.walletforex.dto.WalletDto;
import com.finalproject.walletforex.model.Wallet;

public interface WalletDao {
    Wallet addWallet(WalletDto dto);
    Wallet findByCif(String cif);
}
