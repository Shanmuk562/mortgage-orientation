package com.bank.mortgage.rules;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MortgageRuleEngineTest {

    private MortgageRuleEngine mortgageRuleEngine;

    @BeforeEach
    void setUp() {
        mortgageRuleEngine = new MortgageRuleEngine(List.of(new HomeValueRule(), new IncomeMultipleRule()));
    }

    @Test
   void  testValidateLoanAmountLimit_WhenLoanAmount_IsHigherThan_HomeValue(){
        String reason = mortgageRuleEngine.validateLoanAmountLimit(BigDecimal.valueOf(10000),
                BigDecimal.valueOf(20000),
                BigDecimal.valueOf(5000));

        assertEquals("Loan amount exceeds home value", reason);
    }

    @Test
    void  testValidateLoanAmountLimit_When_LoanAmount_IsMoreThan_4Times_Income(){
        String reason = mortgageRuleEngine.validateLoanAmountLimit(BigDecimal.valueOf(10000),
                BigDecimal.valueOf(700000),
                BigDecimal.valueOf(900000));

        assertEquals("The mortgage amount is high for the given income", reason);
    }
}
