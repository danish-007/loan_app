package com.example.loanApp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import com.example.loanApp.entity.Loan;
import com.example.loanApp.entity.Repayment;
import com.example.loanApp.enums.LoanStatus;
import com.example.loanApp.enums.RepaymentStatus;
import com.example.loanApp.model.SuccessResponse;
import com.example.loanApp.repository.LoanRepository;
import com.example.loanApp.repository.RepaymentRepository;
import com.example.loanApp.service.FixedTermRepaymentStrategy;
import com.example.loanApp.service.RepaymentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RepaymentServiceTest {

    @Mock
    private RepaymentRepository repaymentRepository;

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private RepaymentService repaymentService;

    @InjectMocks
    FixedTermRepaymentStrategy fixedTermRepaymentStrategy;

    @Before
    public void setup() {
        // Mocking behavior if needed
    }

    @Test
    public void testAddRepayment() {
        // Mocking parameters
        Loan loan = new Loan();
        loan.setId(1L);
        String name = "TestUser";

        // Mocking behavior of calculateNextRepaymentAmount
        double nextAmount = 100.0d;
        when(repaymentService.calculateNextRepaymentAmount(loan,fixedTermRepaymentStrategy )).thenReturn(nextAmount);

        // Mocking repaymentRepository behavior
        Repayment repayment = new Repayment();
        repayment.setCreatedBy(name);
        repayment.setModifiedBy(name);
        repayment.setPaymentAmount(nextAmount);
        repayment.setStatus(RepaymentStatus.PENDING);
        repayment.setLoanId(loan.getId());
        when(repaymentRepository.save(any(Repayment.class))).thenReturn(repayment);

        // Testing
        Repayment addedRepayment = repaymentService.addRepayment(loan, name);

        // Assertion
        assertEquals(name, addedRepayment.getCreatedBy());
        assertEquals(name, addedRepayment.getModifiedBy());
        assertEquals(nextAmount, addedRepayment.getPaymentAmount()); // Assuming double comparison
        assertEquals(RepaymentStatus.PENDING, addedRepayment.getStatus());
        assertEquals(loan.getId(), addedRepayment.getLoanId());
//        assertEquals(LocalDate.now().plusDays(7), addedRepayment.getScheduledDate());
    }

    @Test
    public void testMarkRepaymentAsPaid() {
        // Mocking behavior of repositories
        Loan loan = new Loan();
        loan.setId(1L);
        loan.setStatus(LoanStatus.APPROVED);
        loan.setOutstandingAmount(200);
        loan.setTerm(2);
        when(loanRepository.findById(loan.getId())).thenReturn(Optional.of(loan));

        Repayment repayment = new Repayment();
        repayment.setId(1L);
        repayment.setLoanId(1L);
        repayment.setStatus(RepaymentStatus.PENDING);
        repayment.setPaymentAmount(100);
        when(repaymentRepository.findById(repayment.getId())).thenReturn(Optional.of(repayment));
        when(loanRepository.save(any(Loan.class))).thenReturn(loan);


        // Testing
        SuccessResponse successResponse = repaymentService.markRepaymentAsPaid(1L, 100.0, "TestUser");

        // Assertion
        assertEquals(loan, successResponse.getLoans().get(0));
        assertEquals(RepaymentStatus.PAID, repayment.getStatus());
    }
}
