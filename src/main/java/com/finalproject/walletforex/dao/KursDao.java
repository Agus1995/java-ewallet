package com.finalproject.walletforex.dao;

import com.finalproject.walletforex.dto.KursDto;
import com.finalproject.walletforex.model.Kurs;

import java.util.List;

public interface KursDao {
    double buyMoney(String ccy1, String ccy2, double val);
    Kurs updateKurs(Kurs kurs);
    Kurs findByCcy(String ccy1, String ccy2);
    Kurs addNew(KursDto dto);
    Kurs getNewest(KursDto dto);
    List<Kurs> getAll();
    List<Kurs> findGraph(String ccy1, String ccy2);
}
