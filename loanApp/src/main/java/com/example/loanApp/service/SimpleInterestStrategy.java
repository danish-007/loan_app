package com.example.loanApp.service;

import com.example.loanApp.strategy.InterestStrategy;
import org.springframework.stereotype.Service;


@Service
public class SimpleInterestStrategy implements InterestStrategy {
    @Override
    public double calculateInterest(double amount, double rate, int term) {
        return (amount*rate*term)/100;
    }
}
