package com.finalproject.walletforex.controller;

import com.finalproject.walletforex.dao.TraddingDao;
import com.finalproject.walletforex.dto.CommonResponse;
import com.finalproject.walletforex.dto.TraddingDto;
import com.finalproject.walletforex.exception.AccountNotFoundException;
import com.finalproject.walletforex.exception.BalanceNotEnoughException;
import com.finalproject.walletforex.model.ForexTradding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TraddingController {
    private static final String SELL = "/sell"; //path login
    private static final String BUY = "/buy"; //path login
    private static final String GET_SUM = "/tradding/{cif}";
    private static final String GET_REPORT = "/report/{cif}";

    @Autowired
    private TraddingDao traddingDao;

    @GetMapping(path = GET_SUM)
    public CommonResponse<Double> getSum(@PathVariable(value = "cif") String cif){
        CommonResponse<Double> response = new CommonResponse<>();
        Double sum = traddingDao.checksum(cif);
        response.setData(sum);
        return response;
    }

    @GetMapping(path = GET_REPORT)
    public CommonResponse<List<ForexTradding>> getReport(@PathVariable(value = "cif") String cif) throws AccountNotFoundException {
        CommonResponse<List<ForexTradding>> response = new CommonResponse<>();
        List<ForexTradding> forexTraddings = traddingDao.getWithFif(cif);
        response.setData(forexTraddings);
        return response;
    }

    @PostMapping(path = BUY)
    public CommonResponse<ForexTradding> buy(@RequestBody TraddingDto dto) throws AccountNotFoundException, BalanceNotEnoughException {
        CommonResponse<ForexTradding> response = new CommonResponse<>();
        ForexTradding forexTradding = traddingDao.buy(dto);
        response.setData(forexTradding);
        return response;
    }

    @PostMapping(path = SELL)
    public CommonResponse<ForexTradding> sell(@RequestBody TraddingDto dto) throws BalanceNotEnoughException, AccountNotFoundException {
        CommonResponse<ForexTradding> response = new CommonResponse<>();
        ForexTradding forexTradding = traddingDao.sell(dto);
        response.setData(forexTradding);
        return response;
    }
}
