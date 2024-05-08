package com.example.loanApp.controller;

import com.example.loanApp.entity.Repayment;
import com.example.loanApp.model.SuccessResponse;
import com.example.loanApp.service.RepaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/repayments")
public class RepaymentController {

    @Autowired
    private RepaymentService repaymentService;


    @RequestMapping("/{repaymentId}/pay")
    public SuccessResponse markRepaymentAsPaid(@PathVariable Long repaymentId, @RequestParam double amount) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return repaymentService.markRepaymentAsPaid(repaymentId,amount,username);
    }
}