package com.ing.mortgage.rules;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class IncomeMultipleRule implements MortgageRule {

    @Override
    public String validate(BigDecimal income, BigDecimal loanAmount, BigDecimal
            homeValue) {
        if (income == null) return "income is required";
        if(loanAmount == null) return "loanAmount is required";
        var max = income.multiply(BigDecimal.valueOf(4));
        return loanAmount.compareTo(max) <= 0 ? null : "The mortgage amount is high for the given income";
    }
}
