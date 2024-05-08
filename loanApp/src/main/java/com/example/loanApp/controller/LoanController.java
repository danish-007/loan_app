package com.example.loanApp.controller;

import com.example.loanApp.entity.Loan;
import com.example.loanApp.entity.UserInfo;
import com.example.loanApp.model.SuccessResponse;
import com.example.loanApp.repository.UserRepository;
import com.example.loanApp.service.LoanService;
import com.mysql.cj.x.protobuf.Mysqlx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;


    @PostMapping("/create")
    public SuccessResponse createLoan(@RequestParam double amount, @RequestParam int term,@RequestParam double rate) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        SuccessResponse successResponse = loanService.createLoan(amount, term,rate,username);
        return successResponse;
    }
}