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
        Kurs kurs = kursRepository.findByCcy(code);
        return kurs.getSell() * val;
    }

    @Override
    public double buy(String code, double val) {
        Kurs kurs = kursRepository.findByCcy(code);
        return val * kurs.getBuy();
    }

    @Override
    public Kurs getByCode(String code) {
        return kursRepository.findByCcy(code);
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
}
