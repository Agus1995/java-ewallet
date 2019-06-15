package com.finalproject.walletforex;

import com.finalproject.walletforex.dao.KursDao;
import com.finalproject.walletforex.dto.KursDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class KursTest {
//    double buyMoney(String ccy1, String ccy2, double val);
//    Kurs updateKurs(Kurs kurs);
//    Kurs findByCcy(String ccy1, String ccy2);
//    Kurs addNew(KursDto dto);
//    Kurs getNewest(KursDto dto);
//    List<Kurs> getAll();
//    List<Kurs> findGraph(String ccy1, String ccy2);

    @Autowired
    private KursDao kursDao;

    @Before
    public void setDao(){
        KursDto kursDto = new KursDto();
        kursDto.setCcy1("IDR");
        kursDto.setCcy2("USD");
        kursDto.setBuy(15000);
        kursDto.setSell(14000);
        kursDto.setDate(Date.valueOf("2019-01-01"));
        kursDao.addNew(kursDto);
    }

    @Test
    public void getAllTest(){
        assertEquals("IDR", kursDao.getAll().get(0).getCcy1());
    }
    @Test
    public void graphDataTest(){
        assertEquals("USD", kursDao.getAll().get(0).getCcy2());
    }
    @Test
    public void getNewstTest(){
        KursDto kursDto = new KursDto();
        kursDto.setCcy1("IDR");
        kursDto.setCcy2("USD");
        assertEquals("IDR", kursDao.getNewest(kursDto).getCcy1());
    }
    @Test
    public void addTest(){
        KursDto kursDto = new KursDto();
        kursDto.setCcy1("IDR");
        kursDto.setCcy2("JPY");
        kursDto.setBuy(3000);
        kursDto.setSell(2500);
        kursDto.setDate(Date.valueOf("2019-01-01"));
        assertEquals("JPY", kursDao.addNew(kursDto).getCcy2());
    }
    @Test
    public void findKurs(){
        assertEquals("IDR", kursDao.findByCcy("IDR","USD").getCcy1());
    }
    @Test
    public void buyTest(){
        assertEquals(String.format("%s",14000.0), String.valueOf(String.valueOf(kursDao.buyMoney("IDR","USD",1))));
    }
}
