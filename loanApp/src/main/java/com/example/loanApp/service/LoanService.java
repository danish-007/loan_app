package com.example.loanApp.service;

import com.example.loanApp.entity.Loan;
import com.example.loanApp.entity.Repayment;
import com.example.loanApp.entity.UserInfo;
import com.example.loanApp.enums.LoanStatus;
import com.example.loanApp.enums.LoanType;
import com.example.loanApp.model.SuccessResponse;
import com.example.loanApp.strategy.InterestStrategy;
import com.example.loanApp.repository.LoanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    UserService userService;

    @Autowired
    InterestStrategy interestStrategy;

    @Autowired
    RepaymentService repaymentService;

    public SuccessResponse createLoan(double amount, int term,double rate,String username) {
        SuccessResponse successResponse = new SuccessResponse();
        UserInfo user = userService.findByName(username);
        Loan loan = loanRepository.findByUserAndStatus(user.getId(),LoanStatus.PENDING);
        if(!Objects.isNull(loan)){
            return new SuccessResponse(false,"Please wait till the pending loan approves.");
        }
        loan = new Loan();
        loan.setAmount(amount);
        loan.setTerm(term);
        loan.setCreatedBy(user.getName());
        loan.setModifiedBy(user.getName());
        loan.setUser(user.getId());
        loan.setStatus(LoanStatus.PENDING);
        loan.setRate(rate);
        loan.setLoanType(LoanType.PERSONAL);
        loan.setInterest(getInterest(amount,rate,term));
        loan.setOutstandingAmount(loan.getAmount()+loan.getInterest());
        loan= loanRepository.save(loan);
        Repayment repayment = repaymentService.addRepayment(loan,username);
        successResponse.setLoans(loan);
        successResponse.setNextPayment(repayment);
        return successResponse;
    }

    public SuccessResponse approveLoan(Long loanId,LoanStatus status) {
        SuccessResponse successResponse = new SuccessResponse();
        Optional<Loan> optionalLoan = loanRepository.findById(loanId);
        if(optionalLoan.isEmpty()){
            return new SuccessResponse(false,"Invalid user Id");
        }
        Loan loan = optionalLoan.get();
        if(loan.getStatus().equals(status)){
            return new SuccessResponse(false,"Loan is already in status "+status);
        }
        loan.setStatus(status);
        // Update the loan status in the database
        loan = loanRepository.save(loan);
        successResponse.setLoans(loan);
        return successResponse;
    }

    public SuccessResponse getLoansByCustomerId(Long customerId) {
        SuccessResponse successResponse = new SuccessResponse();
        List<Loan> loans = loanRepository.findByUser(customerId);
        successResponse.setLoans(loans);
        return successResponse;
    }
    public SuccessResponse getLoansByStatus(LoanStatus status) {
        SuccessResponse successResponse = new SuccessResponse();
        List<Loan> loan = loanRepository.findByStatus(status);
        if(loan.isEmpty() || loan.size()==0){
            return new SuccessResponse(true,"No record exist");
        }
        successResponse.setLoans(loan);
        return successResponse;
    }
    private double getInterest(double amount,double rate,int term){
        return interestStrategy.calculateInterest(amount,rate,term);
    }

    public SuccessResponse viewLoan(Long customerId){
        SuccessResponse successResponse = new SuccessResponse();
        List<Loan> loans = loanRepository.findByUser(customerId);
        successResponse.setLoans(loans);
        return successResponse;
    }

}
