package com.example.loanApp.strategy;

import com.example.loanApp.entity.Loan;

public interface RepaymentStrategy {
    double calculateNextRepaymentAmount(Loan loan);
}
