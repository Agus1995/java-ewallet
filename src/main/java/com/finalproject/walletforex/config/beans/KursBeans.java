package com.finalproject.walletforex.config.beans;

import com.finalproject.walletforex.dao.KursDao;
import com.finalproject.walletforex.dao.implement.KursDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KursBeans {
    @Bean
    public KursDao kursDao(){
        return new KursDaoImpl();
    }
}
