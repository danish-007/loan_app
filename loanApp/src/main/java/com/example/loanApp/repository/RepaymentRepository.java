package com.example.loanApp.repository;

import com.example.loanApp.entity.Repayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepaymentRepository extends JpaRepository<Repayment, Long> {
    // Add custom methods if needed
}