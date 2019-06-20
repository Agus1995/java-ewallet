package com.finalproject.walletforex.repository;

import com.finalproject.walletforex.model.TraddingReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TraddingReportRepository extends JpaRepository<TraddingReport, Integer> {
    List<TraddingReport> findByCustomer_Cif(String cif);
}
