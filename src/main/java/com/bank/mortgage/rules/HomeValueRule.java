package com.bank.mortgage.rules;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class HomeValueRule implements MortgageRule {
    @Override
    public String validate(BigDecimal income, BigDecimal loanAmount, BigDecimal
            homeValue) {
        if (homeValue == null) return "Home value is required";
        if(loanAmount == null) return "Loan amount is required";
        return loanAmount.compareTo(homeValue) <= 0 ? null : "Loan amount exceeds home value";
    }
}
