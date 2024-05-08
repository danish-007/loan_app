package com.example.loanApp.controller;


import com.example.loanApp.enums.LoanStatus;
import com.example.loanApp.model.SuccessResponse;
import com.example.loanApp.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    LoanService loanService;

    @GetMapping("/{customerId}/view")
    public SuccessResponse approveById(@PathVariable Long customerId) {
        return loanService.getLoansByCustomerId(customerId);
    }

    @GetMapping("/status/{status}/approvals")
    public SuccessResponse approvals(@PathVariable LoanStatus status) {
        return loanService.getLoansByStatus(status);
    }
    @PostMapping("/status/{loanId}/update")
    public SuccessResponse approveLoan(@PathVariable Long loanId,@RequestParam LoanStatus status) {
        return loanService.approveLoan(loanId,status);
    }
}
