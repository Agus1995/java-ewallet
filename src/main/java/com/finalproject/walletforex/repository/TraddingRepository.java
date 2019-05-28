package com.finalproject.walletforex.repository;

import com.finalproject.walletforex.model.ForexTradding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraddingRepository extends JpaRepository<ForexTradding, Integer> {
}
