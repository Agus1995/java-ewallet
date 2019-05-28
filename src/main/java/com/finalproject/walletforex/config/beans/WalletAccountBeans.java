package com.finalproject.walletforex.config.beans;

import com.finalproject.walletforex.dao.WalletAccountDao;
import com.finalproject.walletforex.dao.implement.WalletAccountDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WalletAccountBeans {
    @Bean
    public WalletAccountDao walletAccountDao(){
        return new WalletAccountDaoImpl();
    }
}
