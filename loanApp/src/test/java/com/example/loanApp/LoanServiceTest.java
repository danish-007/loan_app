package com.example.loanApp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import com.example.loanApp.entity.Loan;
import com.example.loanApp.entity.Repayment;
import com.example.loanApp.entity.UserInfo;
import com.example.loanApp.enums.LoanStatus;
import com.example.loanApp.model.SuccessResponse;
import com.example.loanApp.strategy.InterestStrategy;
import com.example.loanApp.repository.LoanRepository;
import com.example.loanApp.service.LoanService;
import com.example.loanApp.service.RepaymentService;
import com.example.loanApp.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.context.SecurityContextHolder;

@RunWith(MockitoJUnitRunner.class)
public class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private UserService userService;

    @Mock
    private RepaymentService repaymentService;

    @Mock
    private InterestStrategy interestStrategy;

    @InjectMocks
    private LoanService loanService;

    @Mock
    SecurityContextHolder mockSecurityContext;

    @Before
    public void setup() {
        // Mocking SecurityContextHolder behavior
//        SecurityContextHolder.getContext().setAuthentication(()->"TESTUSER");
    }

    @Test
    public void testCreateLoan() {
        // Mocking user
//        when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(new );
        UserInfo user = new UserInfo();
        user.setName("TestUser");
        user.setId(1L);
        when(userService.findByName("TestUser")).thenReturn(user);

        // Mocking loanRepository behavior
        when(loanRepository.findByUserAndStatus(user.getId(), LoanStatus.PENDING)).thenReturn(null);
        Loan loan = new Loan();
        loan.setId(1L);
        loan.setTerm(2);
        loan.setAmount(1000);
        loan.setOutstandingAmount(1000);
        loan.setRate(0);
        loan.setInterest(0);
        loan.setUser(user.getId());
        loan.setStatus(LoanStatus.PENDING);
        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        // Mocking interest calculation
        when(interestStrategy.calculateInterest(anyDouble(), anyDouble(), anyInt())).thenReturn(0.00);

        // Mocking repaymentService
        Repayment repayment = new Repayment();
        when(repaymentService.addRepayment(any(Loan.class),anyString())).thenReturn(repayment);

        // Testing
        SuccessResponse successResponse = loanService.createLoan(1000.0, 2, 0,"TestUser");

        // Assertion
        assertNotNull( successResponse.getLoans());
        assertNotNull( successResponse.getNextPayment());
    }

    @Test
    public void testApproveLoan() {
        // Mocking loanRepository behavior
        Loan loan = new Loan();
        loan.setId(1L);
        loan.setStatus(LoanStatus.PENDING);
        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));

        Loan loan1 = new Loan();
        loan1.setStatus(LoanStatus.APPROVED);
        when(loanRepository.save(any(Loan.class))).thenReturn(loan1);
        // Testing
        SuccessResponse successResponse = loanService.approveLoan(1L, LoanStatus.APPROVED);

        // Assertion
        System.out.println(successResponse.getLoans());
        assertEquals(loan.getStatus(), successResponse.getLoans().get(0).getStatus());
    }

    @Test
    public void testGetLoansByCustomerId() {
        // Mocking loanRepository behavior
        Loan loan = new Loan();
        loan.setId(1L);
        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));

        // Testing
        SuccessResponse successResponse = loanService.getLoansByCustomerId(1L);

        // Assertion
        assertEquals(loan.getId(), successResponse.getLoans().get(0).getId());
    }

    @Test
    public void testGetLoansByStatus() {
        // Mocking loanRepository behavior
        Loan loan = new Loan();
        List<Loan> loans = new ArrayList<>();
        loans.add(loan);
        when(loanRepository.findByStatus(LoanStatus.PENDING)).thenReturn(loans);

        // Testing
        SuccessResponse successResponse = loanService.getLoansByStatus(LoanStatus.PENDING);

        // Assertion
        assertEquals(loans, successResponse.getLoans());
    }
}
