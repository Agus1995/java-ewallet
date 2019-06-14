package com.finalproject.walletforex.dto;

import com.finalproject.walletforex.model.Customer;

public class WalletDto {
    private String walletId;
    private String walletName;
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
