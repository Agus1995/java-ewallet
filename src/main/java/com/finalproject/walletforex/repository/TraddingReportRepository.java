package com.finalproject.walletforex.repository;

import com.finalproject.walletforex.model.TraddingReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraddingReportRepository extends JpaRepository<TraddingReport, Integer> {
}
