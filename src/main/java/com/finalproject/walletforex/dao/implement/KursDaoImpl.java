package com.finalproject.walletforex.dao.implement;

import com.finalproject.walletforex.dao.KursDao;
import com.finalproject.walletforex.model.Kurs;
import com.finalproject.walletforex.repository.KursRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class KursDaoImpl implements KursDao {

    @Autowired
    private KursRepository kursRepository;

    @Override
    public double sell(String code, double val) {
        Kurs kurs = kursRepository.findByCcy2(code);
        return kurs.getBuy() * val;
    }

    @Override
    public double buy(String code, double val) {
        Kurs kurs = kursRepository.findByCcy2(code);
        return val / kurs.getSell();
    }

    @Override
    public double buyMoney(String code, double val) {
        Kurs kurs = kursRepository.findByCcy2(code);
        return val * kurs.getSell();
    }

    @Override
    public double getBuyPrice(String code) {
        Kurs kurs = kursRepository.findByCcy2(code);
        return kurs.getBuy();
    }

    @Override
    public double getSellPrice(String code) {
        Kurs kurs = kursRepository.findByCcy2(code);
        return kurs.getSell();
    }

    @Override
    public Kurs getByCode(String code) {
        return kursRepository.findByCcy2(code);
    }

    @Override
    public Kurs updateKurs(Kurs kurs) {
        Kurs kurs1 = kursRepository.findById(kurs.getId())
                .map(ent-> {
                  ent.setSell(kurs.getSell());
                  ent.setBuy(kurs.getBuy());
                  return kursRepository.save(ent);
                }).orElseGet(()->{
                    kurs.setId(kurs.getId());
                    return kursRepository.save(kurs);
                });
        return kurs1;
    }

    @Override
    public Kurs findByCcy(String ccy1, String ccy2) {
        return kursRepository.findNewestKurs(ccy1, ccy2);
    }
}
