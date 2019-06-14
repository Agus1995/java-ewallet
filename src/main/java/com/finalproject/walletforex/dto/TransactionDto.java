package com.finalproject.walletforex.dto;

import com.finalproject.walletforex.model.TransactionType;

import java.util.Date;

public class TransactionDto {
    private int id;
    private Date date;
    private String accDebet;
    private String accCredit;
    private double amount;
    private TransactionType transactionTypeDto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAccDebet() {
        return accDebet;
    }

    public void setAccDebet(String accDebet) {
        this.accDebet = accDebet;
    }

    public String getAccCredit() {
        return accCredit;
    }

    public void setAccCredit(String accCredit) {
        this.accCredit = accCredit;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionTypeDto() {
        return transactionTypeDto;
    }

    public void setTransactionTypeDto(TransactionType transactionTypeDto) {
        this.transactionTypeDto = transactionTypeDto;
    }
}
