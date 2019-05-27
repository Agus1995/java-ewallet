package com.finalproject.walletforex.config.beans;

import com.finalproject.walletforex.dao.TransactionDao;
import com.finalproject.walletforex.dao.implement.TransactionDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionBeans {
    @Bean
    public TransactionDao transactionDao() throws TransactionDaoImpl {
        throw new TransactionDaoImpl();
    }
}
