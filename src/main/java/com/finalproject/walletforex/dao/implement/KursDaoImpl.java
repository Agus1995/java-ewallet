package com.finalproject.walletforex.dao.implement;

import com.finalproject.walletforex.dao.KursDao;
import com.finalproject.walletforex.dto.KursDto;
import com.finalproject.walletforex.model.Kurs;
import com.finalproject.walletforex.repository.KursRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class KursDaoImpl implements KursDao {

    @Autowired
    private KursRepository kursRepository;

    @Override
    public double buyMoney(String ccy1, String ccy2, double val) {
        Kurs kurs = kursRepository.findNewestKurs(ccy1, ccy2);
        return val * kurs.getSell();
    }

    @Override
    public Kurs findByCcy(String ccy1, String ccy2) {
        return kursRepository.findNewestKurs(ccy1, ccy2);
    }

    @Override
    public Kurs addNew(KursDto dto) {
        Kurs kurs = setDto(dto);
        return kursRepository.save(kurs);
    }

    @Override
    public Kurs getNewest(KursDto dto) {
        return kursRepository.findNewestKurs(dto.getCcy1(), dto.getCcy2());
    }

    @Override
    public List<Kurs> getAll() {
        return kursRepository.findAll();
    }

    @Override
    public List<Kurs> findGraph(String ccy1, String ccy2) {
        return kursRepository.findByCcy1AndCcy2(ccy1,ccy2);
    }

    private Kurs setDto(KursDto dto){
        Kurs kurs = new Kurs();
        kurs.setBuy(dto.getBuy());
        kurs.setSell(dto.getSell());
        kurs.setCcy1(dto.getCcy1());
        kurs.setCcy2(dto.getCcy2());
        kurs.setDate(dto.getDate());
        kurs.setId(dto.getId());
        return kurs;
    }
}
