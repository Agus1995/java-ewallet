package com.finalproject.walletforex.controller;

import com.finalproject.walletforex.dao.AccountDao;
import com.finalproject.walletforex.dto.AccountDto;
import com.finalproject.walletforex.dto.CommonResponse;
import com.finalproject.walletforex.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class AccountController {
    private static final String ADD_ACCOUNT = "/account"; //path Add Account
    private static final String GET_ACCOUNT = "/customer/{cif}/account"; //path Get List Account By cif

    @Autowired
    private AccountDao accountDao;

    @PostMapping(path = ADD_ACCOUNT)
    public CommonResponse<Account> add(@RequestBody AccountDto dto){
        CommonResponse<Account> response = new CommonResponse<>();
        Account account = accountDao.addAccount(dto);
        response.setData(account);
        return response;
    }

    @GetMapping(path = GET_ACCOUNT)
    public CommonResponse<List<Account>>getByCif(@PathVariable(value = "cif") String cif){
        CommonResponse<List<Account>> response = new CommonResponse<>();
        List<Account> account = accountDao.findByCif(cif);
        response.setData(account);
        return response;
    }
}
