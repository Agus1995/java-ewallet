package com.finalproject.walletforex.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_kurs")
public class Kurs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String ccy;
    private double sell;
    private double buy;

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
