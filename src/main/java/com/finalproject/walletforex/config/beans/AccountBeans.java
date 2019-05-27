package com.finalproject.walletforex.config.beans;

import com.finalproject.walletforex.dao.AccountDao;
import com.finalproject.walletforex.dao.implement.AccountDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountBeans {
    @Bean
    public AccountDao accountDao(){
        return new AccountDaoImpl();
    }
}
