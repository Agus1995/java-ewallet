package com.finalproject.walletforex.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "tb_tradding_report")
public class TraddingReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date date;
    private Double rate;
    @ManyToOne
    @JoinColumn(name = "cif")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

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

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
