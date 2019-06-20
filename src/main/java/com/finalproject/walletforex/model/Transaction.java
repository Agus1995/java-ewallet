package com.finalproject.walletforex.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "tb_transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
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
