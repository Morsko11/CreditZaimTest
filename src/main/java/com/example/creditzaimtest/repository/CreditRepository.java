package com.example.creditzaimtest.repository;

import com.example.creditzaimtest.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CreditRepository extends JpaRepository<Credit, Integer> {

    @Query("SELECT c FROM Credit c ORDER BY c.firstPaymentDate")
    List<Credit> findAllAndOrderByFirstPaymentDate();
}