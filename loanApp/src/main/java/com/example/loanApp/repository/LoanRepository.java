package com.example.loanApp.repository;

import com.example.loanApp.entity.Loan;
import com.example.loanApp.enums.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByStatus(LoanStatus status);
    Loan findByUserAndStatus(Long id,LoanStatus status);
    List<Loan> findByUser(Long id);

}
