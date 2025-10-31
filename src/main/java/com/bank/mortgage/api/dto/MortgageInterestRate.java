package com.bank.mortgage.api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class MortgageInterestRate {
    private Long id;
    private BigDecimal interestRate;
    private Integer maturityPeriod;
    private Timestamp lastUpdated;

    public MortgageInterestRate(final long id, final BigDecimal interestRate, final int maturityPeriod, final Timestamp lastUpdated) {
        this.id = id;
        this.interestRate = interestRate;
        this.maturityPeriod = maturityPeriod;
        this.lastUpdated = lastUpdated;
    }
}
