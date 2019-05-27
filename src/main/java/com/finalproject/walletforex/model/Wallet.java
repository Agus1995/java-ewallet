package com.finalproject.walletforex.model;

import javax.persistence.*;

@Entity
@Table (name = "tb_wallet")
public class Wallet {
    @Id
    @Column(name = "wallet_id")
    private String walletId;
    @Column(name = "wallet_name")
    private String walletName;
    @ManyToOne
    @JoinColumn(name = "cif")
    private Customer customer;

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
