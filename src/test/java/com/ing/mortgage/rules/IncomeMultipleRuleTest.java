package com.ing.mortgage.rules;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class IncomeMultipleRuleTest {
    @Test
    void passes_when_loan_within_4x_income() {
        var rule = new IncomeMultipleRule();
        assertNull(rule.validate(BigDecimal.valueOf(30000), BigDecimal.valueOf(50000),
                BigDecimal.valueOf(500000)));
    }

    @Test
    void fails_when_loan_exceeds_4x_income() {
        var rule = new IncomeMultipleRule();
        assertEquals("The mortgage amount is high for the given income", rule.validate(
                BigDecimal.valueOf(50000), BigDecimal.valueOf(250001), BigDecimal.valueOf(500000)));
    }
}
