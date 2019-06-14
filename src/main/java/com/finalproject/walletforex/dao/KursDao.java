package com.finalproject.walletforex.dao;

import com.finalproject.walletforex.model.Kurs;

public interface KursDao {
    double buyMoney(String ccy1, String ccy2, double val);
    Kurs updateKurs(Kurs kurs);
    Kurs findByCcy(String ccy1, String ccy2);
}
