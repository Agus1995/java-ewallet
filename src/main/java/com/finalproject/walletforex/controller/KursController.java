package com.finalproject.walletforex.controller;

import com.finalproject.walletforex.dao.KursDao;
import com.finalproject.walletforex.dto.CommonResponse;
import com.finalproject.walletforex.dto.KursDto;
import com.finalproject.walletforex.model.Kurs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KursController {
    private static final String ADD = "/kurs";
    private static final String GET_ALL = "/kurs";
    private static final String GET_NEW = "/kurs/new";
    private static final String GRAPH = "/kurs/graph";

    @Autowired
    private KursDao kursDao;

    @PostMapping(path = ADD)
    public CommonResponse<Kurs> update(@RequestBody KursDto dto) {
        CommonResponse<Kurs> response = new CommonResponse<>();
        Kurs kurs = kursDao.addNew(dto);
        response.setData(kurs);
        return response;
    }

    @GetMapping(path = GET_ALL)
    public CommonResponse<List<Kurs>> getAll() {
        CommonResponse<List<Kurs>> response = new CommonResponse<>();
        List<Kurs> kurs = kursDao.getAll();
        response.setData(kurs);
        return response;
    }

    @GetMapping(path = GET_NEW)
    @ResponseBody
    public CommonResponse<Kurs> getNew(@RequestParam(name = "ccy1") String ccy1, @RequestParam(name = "ccy2") String ccy2) {
        KursDto dto = new KursDto();
        dto.setCcy1(ccy1);
        dto.setCcy2(ccy2);
        CommonResponse<Kurs> response = new CommonResponse<>();
        Kurs kurs = kursDao.getNewest(dto);
        response.setData(kurs);
        return response;
    }

    @GetMapping(path = GRAPH)
    @ResponseBody
    public CommonResponse<List<Kurs>> getGraph(@RequestParam(name = "ccy1") String ccy1, @RequestParam(name = "ccy2") String ccy2) {
        CommonResponse<List<Kurs>> response = new CommonResponse<>();
        List<Kurs> kurs = kursDao.findGraph(ccy1,ccy2);
        response.setData(kurs);
        return response;
    }
}
