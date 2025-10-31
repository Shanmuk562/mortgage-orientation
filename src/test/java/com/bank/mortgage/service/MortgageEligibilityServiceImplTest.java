package com.bank.mortgage.service;

import com.bank.mortgage.api.dto.MortgageEligibilityCheckRequest;
import com.bank.mortgage.api.dto.MortgageEligibilityCheckResponse;
import com.bank.mortgage.api.dto.MortgageInterestRate;
import com.bank.mortgage.api.exception.CustomValidationException;
import com.bank.mortgage.api.exception.NotFoundException;
import com.bank.mortgage.entity.model.InterestRateEntity;
import com.bank.mortgage.mapper.MortgageInterestRateMapper;
import com.bank.mortgage.repository.MortgageInterestRateRepository;
import com.bank.mortgage.rules.MortgageRuleEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MortgageEligibilityServiceImplTest {

    private MortgageEligibilityServiceImpl mortgageEligibilityService;
    private MortgageInterestRateRepository mortgageInterestRateRepository;
    private MortgageInterestRateMapper     interestRateMapper;
    MortgageEligibilityCheckRequest request;
    MortgageInterestRate mortgageRate;
    InterestRateEntity   rateEntity;

    @BeforeEach
    void setUp() {
        interestRateMapper = Mockito.mock(MortgageInterestRateMapper.class);
        mortgageInterestRateRepository = Mockito.mock(MortgageInterestRateRepository.class);
        MortgageRuleEngine rules = Mockito.mock(MortgageRuleEngine.class);
        mortgageEligibilityService = new MortgageEligibilityServiceImpl(mortgageInterestRateRepository, rules);
        request = MortgageEligibilityCheckRequest.builder()
                .homeValue(BigDecimal.valueOf(10000))
                .income(BigDecimal.valueOf(20000))
                .maturityPeriod(10)
                .loanAmount(BigDecimal.valueOf(5000)).build();
        mortgageRate =
                new MortgageInterestRate(2L, BigDecimal.valueOf(10.1), 10, Timestamp.from(Instant.now()));
        rateEntity = new InterestRateEntity();
        rateEntity.setId(1L);
        rateEntity.setInterestRate(BigDecimal.valueOf(10.1));
        rateEntity.setMaturityPeriod(2);
        rateEntity.setLastUpdated(Timestamp.from(Instant.now()));

    }

    @Test
    public void testMortgageEligibilityCheck_Success() {
        when(mortgageInterestRateRepository.findByMaturityPeriod(request.maturityPeriod())).
                thenReturn(Optional.of(rateEntity));
        when(interestRateMapper.toMortgageInterestRate(any())).thenReturn(mortgageRate);

        MortgageEligibilityCheckResponse response = mortgageEligibilityService.checkEligibility(request);
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.eligible());
        Assertions.assertEquals(BigDecimal.valueOf(10.1), response.interestRate());

    }

    @Test
    public void testMortgageCheckIf_NoInterestRateFound() {
        when(mortgageInterestRateRepository.findByMaturityPeriod(request.maturityPeriod())).
                thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> mortgageEligibilityService.checkEligibility(request));
    }

    @Test
    public void testMortgageCheckIf_NoFeasibility() {
        request = MortgageEligibilityCheckRequest.builder()
                .homeValue(BigDecimal.valueOf(10000))
                .income(BigDecimal.ZERO)
                .maturityPeriod(3)
                .loanAmount(BigDecimal.valueOf(5000)).build();
        Assertions.assertThrows(NotFoundException.class, () -> mortgageEligibilityService.checkEligibility(request));
    }

    @Test
    public void testMortgageEligibilityCheck_Fail_high_maturity_period() {
        request = MortgageEligibilityCheckRequest.builder()
                .homeValue(BigDecimal.valueOf(10000))
                .income(BigDecimal.valueOf(10000))
                .maturityPeriod(3000)
                .loanAmount(BigDecimal.valueOf(5000)).build();
        Assertions.assertThrows(CustomValidationException.class, () -> mortgageEligibilityService.checkEligibility(request));

    }
}
