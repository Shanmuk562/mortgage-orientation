package com.ing.mortgage.api.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder(toBuilder = true)
public record MortgageEligibilityCheckResponse(boolean eligible,
                                               String reason,
                                               BigDecimal interestRate) {
}
