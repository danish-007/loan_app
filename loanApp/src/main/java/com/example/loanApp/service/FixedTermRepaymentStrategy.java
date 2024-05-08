package com.example.loanApp.service;


import com.example.loanApp.entity.Loan;
import com.example.loanApp.strategy.RepaymentStrategy;
import org.springframework.stereotype.Service;

@Service
public class FixedTermRepaymentStrategy implements RepaymentStrategy {
    @Override
    public double calculateNextRepaymentAmount(Loan loan) {
        return loan.getOutstandingAmount()/(loan.getTerm()-loan.getTermCompleted());
    }
}
