package com.example.loanApp.service;

import com.example.loanApp.entity.Loan;
import com.example.loanApp.entity.Repayment;
import com.example.loanApp.enums.LoanStatus;
import com.example.loanApp.enums.RepaymentStatus;
import com.example.loanApp.model.SuccessResponse;
import com.example.loanApp.repository.LoanRepository;
import com.example.loanApp.repository.RepaymentRepository;

import com.example.loanApp.strategy.RepaymentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RepaymentService {

    @Autowired
    private RepaymentRepository repaymentRepository;

    @Autowired
    private LoanRepository loanRepository;


    public Repayment addRepayment(Loan loan,String name) {
        double nextAmount = calculateNextRepaymentAmount(loan,new FixedTermRepaymentStrategy());
        Repayment repayment = new Repayment();
        repayment.setCreatedBy(name);
        repayment.setModifiedBy(name);
        repayment.setPaymentAmount(nextAmount);
        repayment.setStatus(RepaymentStatus.PENDING);
        repayment.setLoanId(loan.getId());
        repayment.setScheduledDate(LocalDate.now().plusDays(7));// Save the repayment to the database
        repaymentRepository.save(repayment);
        return repayment;
    }

    public SuccessResponse markRepaymentAsPaid(Long repaymentId, double amount,String username) {
        SuccessResponse successResponse  = new SuccessResponse();
        Repayment repayment = repaymentRepository.findById(repaymentId).get();
        Loan loan = loanRepository.findById(repayment.getLoanId()).get();
        if(LoanStatus.PENDING.equals(loan.getStatus())){
            return new SuccessResponse(false,"Approval is pending for loan id"+loan.getId());
        }
        if(loan.getOutstandingAmount() < amount){
            return new SuccessResponse(false,"Please pay the lesser amount, current outstanding loan amount is"+loan.getOutstandingAmount());
        }
        loan.setOutstandingAmount(loan.getOutstandingAmount() - amount);
        loan.setTermCompleted(loan.getTermCompleted()+1);
        if(loan.getOutstandingAmount() == 0){
            loan.setStatus(LoanStatus.PAID);
        }
        repayment.setStatus(RepaymentStatus.PAID);
        repayment.setPaidAmount(amount);
        repayment.setPaidDate(LocalDate.now());
        // Update the repayment status in the database
        repaymentRepository.save(repayment);
        loan = loanRepository.save(loan);
        successResponse.setLoans(loan);
        if(loan.getOutstandingAmount() > 0){
            Repayment newRepayment = addRepayment(loan,username);
            successResponse.setNextPayment(newRepayment);
        }
        return successResponse;

    }
    public double calculateNextRepaymentAmount(Loan loan,RepaymentStrategy repaymentStrategy){
        return repaymentStrategy.calculateNextRepaymentAmount(loan);
    }
}
