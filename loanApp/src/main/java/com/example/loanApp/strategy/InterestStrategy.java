package com.example.loanApp.strategy;

public interface InterestStrategy {
    double calculateInterest(double amount,double rate,int term);
}
