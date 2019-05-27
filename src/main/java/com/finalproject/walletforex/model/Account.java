package com.finalproject.walletforex.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_account")
public class Account {
    @Id
    @Column(name = "account_number")
    private String accountNumber;
    private String name;
    private double balance;
    @ManyToOne
    @JoinColumn(name = "cif")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
}
