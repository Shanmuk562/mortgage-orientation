package com.bank.mortgage.rules;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class HomeValueRuleTest {
    @Test
    void passes_when_loan_leq_home_value() {
        var rule = new HomeValueRule();
        assertNull(rule.validate(BigDecimal.valueOf(50000), BigDecimal.valueOf(100000), BigDecimal.valueOf(150000)));
    }
    @Test
    void fails_when_loan_gt_home_value() {
        var rule = new HomeValueRule();
        assertEquals("Loan amount exceeds home value", rule.validate(BigDecimal.valueOf(50000),
                BigDecimal.valueOf(300000), BigDecimal.valueOf(270000)));
    }
}
