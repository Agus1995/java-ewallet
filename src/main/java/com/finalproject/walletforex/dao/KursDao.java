package com.finalproject.walletforex.dao;

import com.finalproject.walletforex.model.Kurs;

public interface KursDao {
    double sell(String code, double val);
    double buy(String code, double val);
    double buyMoney(String code, double val);
    double getBuyPrice(String code);
    double getSellPrice(String code);
    Kurs getByCode(String code);
    Kurs updateKurs(Kurs kurs);
}
