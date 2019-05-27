package com.finalproject.walletforex.model;

import javax.persistence.*;

@Entity
@Table (name = "tb_wallet_account")
public class WalletAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "accountNumber")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "walletId")
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