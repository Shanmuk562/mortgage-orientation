package com.ing.mortgage.rules;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MortgageRuleEngine {

    private final List<MortgageRule> rules;

    public MortgageRuleEngine(List<MortgageRule> rules) {
        this.rules = rules;
    }

    public String validateLoanAmountLimit(BigDecimal income, BigDecimal loanAmount,
                                          BigDecimal homeValue) {
        for (var rule : rules) {
            var reason = rule.validate(income, loanAmount, homeValue);
            if (reason != null) return reason;
        }
        return null;
    }
}
