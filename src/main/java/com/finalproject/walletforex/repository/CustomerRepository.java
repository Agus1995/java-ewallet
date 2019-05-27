package com.finalproject.walletforex.repository;

import com.finalproject.walletforex.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findByUsername(String username);
}
