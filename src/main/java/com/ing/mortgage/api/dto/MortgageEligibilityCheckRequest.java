package com.ing.mortgage.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;

import static com.ing.mortgage.constants.MortgageEligibilityConstants.INVALID_HOME_VALUE;
import static com.ing.mortgage.constants.MortgageEligibilityConstants.INVALID_INCOME;
import static com.ing.mortgage.constants.MortgageEligibilityConstants.INVALID_LOAN_AMOUNT;
import static com.ing.mortgage.constants.MortgageEligibilityConstants.INVALID_MATURITY;

@Builder(toBuilder = true)
public record MortgageEligibilityCheckRequest(@NotNull(message = INVALID_INCOME) @Positive(message = INVALID_INCOME)
                                              BigDecimal income,
                                              @NotNull(message = INVALID_MATURITY) @Positive(message = INVALID_MATURITY)
                                              Integer maturityPeriod,
                                              @NotNull(message = INVALID_LOAN_AMOUNT) @Positive(message = INVALID_LOAN_AMOUNT)
                                              BigDecimal loanAmount,
                                              @NotNull(message = INVALID_HOME_VALUE) @Positive(message = INVALID_HOME_VALUE)
                                              BigDecimal homeValue) {
}
