package com.finalproject.walletforex.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "tb_kurs")
public class Kurs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String ccy1;
    private String ccy2;
    private double sell;
    private double buy;
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCcy1() {
        return ccy1;
    }

    public void setCcy1(String ccy1) {
        this.ccy1 = ccy1;
    }

    public String getCcy2() {
        return ccy2;
    }

    public void setCcy2(String ccy2) {
        this.ccy2 = ccy2;
    }

    public double getSell() {
        return sell;
    }

    public void setSell(double sell) {
        this.sell = sell;
    }

    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }
}
