package com.example.loanApp.entity;

import com.example.loanApp.enums.RepaymentStatus;
import org.springframework.data.annotation.CreatedDate;


import java.time.LocalDate;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Repayment extends AuditedEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "decimal(15,4)")
    private double paymentAmount;

    @Column(columnDefinition = "decimal(15,4)")
    private double paidAmount;


    private LocalDate paidDate;

    private LocalDate scheduledDate;

    @Enumerated(EnumType.STRING)
    private RepaymentStatus status;

    private Long loanId;



    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return paymentAmount;
    }

    public void setPaymentAmountpayment(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }



    public RepaymentStatus getStatus() {
        return status;
    }

    public void setStatus(RepaymentStatus status) {
        this.status = status;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }
}
