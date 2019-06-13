package com.finalproject.walletforex.dto;

import com.finalproject.walletforex.model.Customer;

public class TraddingDto {
    private int id;
    private String ccy;
    private String description;
    private Double rate;
    private Double restOfMoney;
    private Double amount;
    private Double provitLost;
    private String account;
    private Customer customer;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getRestOfMoney() {
        return restOfMoney;
    }

    public void setRestOfMoney(Double restOfMoney) {
        this.restOfMoney = restOfMoney;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getProvitLost() {
        return provitLost;
    }

    public void setProvitLost(Double provitLost) {
        this.provitLost = provitLost;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
