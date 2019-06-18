package com.finalproject.walletforex.repository;

import com.finalproject.walletforex.model.ForexTradding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TraddingRepository extends JpaRepository<ForexTradding, Integer> {
    @Query("SELECT f FROM ForexTradding f WHERE f.description = 'B' AND f.restOfMoney > 0 AND f.customer.cif= :cif")
    List<ForexTradding> findByCiff(@Param("cif") String cif);

    @Query("SELECT SUM(f.restOfMoney) FROM ForexTradding f WHERE f.restOfMoney>0 AND f.description= 'B' AND f.customer.cif= :cif")
    double getSum(@Param("cif") String cif);

    List<ForexTradding> findByCif(String cif);
}
