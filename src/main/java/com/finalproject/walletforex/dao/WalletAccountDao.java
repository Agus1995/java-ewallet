package com.finalproject.walletforex.dao;

import com.finalproject.walletforex.dto.WalletAccountDto;
import com.finalproject.walletforex.model.WalletAccount;

import java.util.List;

public interface WalletAccountDao {
    WalletAccount register(WalletAccountDto dto);
    List<WalletAccount> getRegistered(String cif);
    void unreg(int id);
}
