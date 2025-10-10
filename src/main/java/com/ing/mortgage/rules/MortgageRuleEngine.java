package com.ing.mortgage.rules;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class MortgageRuleEngine {

    private final List<MortgageRule> rules;

    public MortgageRuleEngine(List<MortgageRule> rules) {
        this.rules = rules;
    }

    public String validateLoanAmountLimit(BigDecimal income, BigDecimal loanAmount,
                                          BigDecimal homeValue) {
        return rules.stream().map(rule -> rule.validate(income, loanAmount, homeValue))
                .filter(Objects::nonNull).findFirst().orElse(null);
    }
}
