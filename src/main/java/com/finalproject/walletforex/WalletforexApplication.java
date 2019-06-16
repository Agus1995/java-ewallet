package com.finalproject.walletforex;

import com.finalproject.walletforex.config.beans.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories({"com.finalproject.walletforex.repository"})
@EntityScan({"com.finalproject.walletforex.model"})
@Import({AccountBeans.class, CustomerBeans.class, WalletAccountBeans.class, WalletBeans.class, TransactionBeans.class, KursBeans.class, TraddingBeans.class})
public class WalletforexApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalletforexApplication.class, args);
	}

}
