package com.ing.mortgage.rules;

import java.math.BigDecimal;

public interface MortgageRule {
    /** Return null if valid; otherwise return a human-readable reason. */
    String validate(BigDecimal income, BigDecimal loanAmount, BigDecimal
            homeValue);
}
