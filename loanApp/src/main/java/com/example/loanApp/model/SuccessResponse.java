package com.example.loanApp.model;


import com.example.loanApp.entity.Loan;
import com.example.loanApp.entity.Repayment;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuccessResponse {
    private boolean status=true;
    private String message;
    private List<Loan> loans;

    private Repayment nextPayment;

    public Repayment getNextPayment() {
        return nextPayment;
    }

    public void setNextPayment(Repayment nextPayment) {
        this.nextPayment = nextPayment;
    }

    public SuccessResponse(){
    }
    public SuccessResponse(List<Loan> loans){
        this.loans = loans;
    }
    public SuccessResponse(boolean status,String message){
        this.message = message;
        this.status = status;
    }
    public void setLoans(Object object){
        if(object instanceof Loan){
            if(loans == null){
                loans = new ArrayList<>();
            }
            loans.add((Loan) object);
        }else {
            this.loans= (List)object;
        }
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }
}
