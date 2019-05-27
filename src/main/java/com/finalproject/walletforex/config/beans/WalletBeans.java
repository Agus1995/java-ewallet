package com.finalproject.walletforex.config.beans;

import com.finalproject.walletforex.dao.WalletDao;
import com.finalproject.walletforex.dao.implement.WalletDaoImpl;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WalletBeans {
    public WalletDao walletDao(){
        return new WalletDaoImpl();
    }
}
