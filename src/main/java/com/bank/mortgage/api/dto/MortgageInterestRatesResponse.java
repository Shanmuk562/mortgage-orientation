package com.bank.mortgage.api.dto;

import lombok.Builder;

import java.util.List;

@Builder(toBuilder = true)
public record MortgageInterestRatesResponse(List<MortgageInterestRate> interestRates) {
}
