package com.finalproject.walletforex.config.beans;

import com.finalproject.walletforex.dao.CustomerDao;
import com.finalproject.walletforex.dao.implement.CustomerDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerBeans {
    @Bean
    public CustomerDao customerDao(){
        return new CustomerDaoImpl();
    }
}
