package com.finalproject.walletforex.dto;

import com.finalproject.walletforex.model.Account;
import com.finalproject.walletforex.model.Wallet;

public class WalletAccountDto {
    private int id;
    private Account account;
    private Wallet wallet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
