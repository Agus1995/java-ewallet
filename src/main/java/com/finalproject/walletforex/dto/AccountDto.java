package com.finalproject.walletforex.dto;

import com.finalproject.walletforex.model.Customer;

public class AccountDto {
    private String accountNumber;
    private String name;
    private double balance;
    private String curencyType;
    private String currencyType;
    private Customer customer;

    public String getCurencyType() {
        return curencyType;
    }

    public void setCurencyType(String curencyType) {
        this.curencyType = curencyType;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
