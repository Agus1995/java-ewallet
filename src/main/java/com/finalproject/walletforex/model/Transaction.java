package com.finalproject.walletforex.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Transaction {
    private int id;
    private Date date;
    private String accDebet;
    private String accCredit;
    private double amount;
    @ManyToOne
    @JoinColumn(name = "type")
    private TransactionType transactionType;

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

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
