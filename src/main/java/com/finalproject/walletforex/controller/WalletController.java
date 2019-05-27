package com.finalproject.walletforex.controller;

import com.finalproject.walletforex.dao.WalletDao;
import com.finalproject.walletforex.dto.CommonResponse;
import com.finalproject.walletforex.dto.WalletDto;
import com.finalproject.walletforex.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WalletController {

    @Autowired
    private WalletDao walletDao;

    private static final String ADD_WALLET = "/wallet"; //path Add Account
    private static final String GET_WALLET = "/customer/{cif}/wallets"; //path Get List Account By cif

    @PostMapping(path = ADD_WALLET)
    public CommonResponse<Wallet> add(@RequestBody WalletDto dto){
        CommonResponse<Wallet> response = new CommonResponse<>();
        Wallet account = walletDao.addWallet(dto);
        response.setData(account);
        return response;
    }

    @GetMapping(path = GET_WALLET)
    public CommonResponse<List<Wallet>>getByCif(@PathVariable(value = "cif") String cif){
        CommonResponse<List<Wallet>> response = new CommonResponse<>();
        List<Wallet> account = walletDao.findByCif(cif);
        response.setData(account);
        return response;
    }
}
