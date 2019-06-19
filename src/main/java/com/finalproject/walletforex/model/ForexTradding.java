package com.finalproject.walletforex.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tb_tradding")
public class ForexTradding {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "date", insertable=false,updatable=false)
    private Timestamp date;
    private String ccy;
    private String description;
    private Double rate;
    private Double restOfMoney;
    private Double amount;
    private Double provitLost;
    @ManyToOne
    @JoinColumn(name = "cif")
    private Customer customer;

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
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
