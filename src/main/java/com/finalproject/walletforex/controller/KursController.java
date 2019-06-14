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
    public CommonResponse<Kurs> getNew(@RequestBody KursDto dto) {
        CommonResponse<Kurs> response = new CommonResponse<>();
        Kurs kurs = kursDao.getNewest(dto);
        response.setData(kurs);
        return response;
    }

    @GetMapping(path = GRAPH)
    @ResponseBody
    public CommonResponse<List<Kurs>> getGraph(@RequestParam(name = "id") String ccy1, @RequestParam String ccy2) {
        CommonResponse<List<Kurs>> response = new CommonResponse<>();
        List<Kurs> kurs = kursDao.findGraph(ccy1,ccy2);
        response.setData(kurs);
        return response;
    }
}
