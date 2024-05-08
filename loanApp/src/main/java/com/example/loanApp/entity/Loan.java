package com.example.loanApp.entity;

import com.example.loanApp.enums.LoanStatus;
import com.example.loanApp.enums.LoanType;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

@Entity
public class Loan extends AuditedEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "decimal(15,4)")
    private double amount;

    private int term;

    @Column(columnDefinition = "decimal(15,4)" )
    private double rate;

    @Column(columnDefinition = "decimal(15,4)" )
    private double interest;

    @Column(columnDefinition = "decimal(15,4)" )
    private double outstandingAmount;


    private int termCompleted;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    @Enumerated(EnumType.STRING)
    private LoanType loanType ;

    @Column(name = "user_id")
    Long user;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getOutstandingAmount() {
        return outstandingAmount;
    }

    public void setOutstandingAmount(double outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
    }

    public int getTermCompleted() {
        return termCompleted;
    }

    public void setTermCompleted(int termCompleted) {
        this.termCompleted = termCompleted;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }
}