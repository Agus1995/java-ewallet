package com.finalproject.walletforex.controller;

import com.finalproject.walletforex.dao.WalletAccountDao;
import com.finalproject.walletforex.dto.CommonResponse;
import com.finalproject.walletforex.dto.WalletAccountDto;
import com.finalproject.walletforex.exception.AccountNotFoundException;
import com.finalproject.walletforex.model.WalletAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class WalletAccountController {
    @Autowired
    private WalletAccountDao walletAccountDao;

    private static final String REGISTER_WALLET = "/wallet-account"; //path Add Account
    private static final String UNREGISTER_WALLET = "/wallet-account/{id}"; //path Get List Account By cif
    private static final String LIST_REGISTERED = "/customer/{cif}/wallet-accounts"; //path Add Account
    private static final String LIST_BY_WALLET = "/wallet/{id}/wallet-accounts"; //path Add Account



    @PostMapping(path = REGISTER_WALLET)
    public CommonResponse<WalletAccount> add(@RequestBody WalletAccountDto dto){
        CommonResponse<WalletAccount> response = new CommonResponse<>();
        WalletAccount account = walletAccountDao.register(dto);
        response.setData(account);
        return response;
    }

    @DeleteMapping(path = UNREGISTER_WALLET)
    public CommonResponse<Void> unreg(@PathVariable(value = "id") Integer id){
        CommonResponse<Void> response = new CommonResponse<>();
        walletAccountDao.unreg(id);
        return response;
    }

    @GetMapping(path = LIST_REGISTERED)
    public CommonResponse<List<WalletAccount>> unreg(@PathVariable(value = "cif") String cif) throws AccountNotFoundException, InterruptedException {
        CommonResponse<List<WalletAccount>> response = new CommonResponse<>();
        List<WalletAccount> walletAccounts = walletAccountDao.getRegistered(cif);
        response.setData(walletAccounts);
        return response;
    }

    @GetMapping(path = LIST_BY_WALLET)
    public  CommonResponse<List<WalletAccount>> findByWallet(@PathVariable(value = "id") String id){
        CommonResponse<List<WalletAccount>> response = new CommonResponse<>();
        List<WalletAccount> walletAccounts = walletAccountDao.findByWallet(id);
        response.setData(walletAccounts);
        return response;
    }
}
