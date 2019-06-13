package com.finalproject.walletforex.dao.implement;

import com.finalproject.walletforex.dao.KursDao;
import com.finalproject.walletforex.model.Kurs;
import com.finalproject.walletforex.repository.KursRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class KursDaoImpl implements KursDao {

    @Autowired
    private KursRepository kursRepository;

    @Override
    public double buyMoney(String ccy1, String ccy2, double val) {
        Kurs kurs = kursRepository.findByCcy1AndCcy2(ccy1, ccy2);
        return val * kurs.getSell();
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
