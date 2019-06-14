package com.finalproject.walletforex.config.beans;

import com.finalproject.walletforex.dao.TraddingDao;
import com.finalproject.walletforex.dao.implement.TraddingDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TraddingBeans {
    @Bean
    public TraddingDao traddingDao(){
        return new TraddingDaoImpl();
    }
}
