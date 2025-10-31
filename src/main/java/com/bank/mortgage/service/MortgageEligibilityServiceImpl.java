package com.bank.mortgage.service;

import com.bank.mortgage.api.dto.MortgageEligibilityCheckRequest;
import com.bank.mortgage.api.dto.MortgageEligibilityCheckResponse;
import com.bank.mortgage.api.exception.CustomValidationException;
import com.bank.mortgage.api.exception.NotFoundException;
import com.bank.mortgage.repository.MortgageInterestRateRepository;
import com.bank.mortgage.rules.MortgageRuleEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static com.bank.mortgage.constants.MortgageEligibilityConstants.ELIGIBLE;
import static com.bank.mortgage.constants.MortgageEligibilityConstants.INVALID_MATURITY;
import static com.bank.mortgage.constants.MortgageEligibilityConstants.MAX_MATURITY_PERIOD;
import static com.bank.mortgage.constants.MortgageEligibilityConstants.RATE_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class MortgageEligibilityServiceImpl implements MortgageEligibilityService {

    private final MortgageInterestRateRepository rateRepo;
    private final MortgageRuleEngine             rules;

    /**
     * This method checks the mortgage eligibility
     * @param request with income, maturityPeriod, loanAmount, homeValue
     * @return MortgageEligibilityCheckResponse
     */
    @Async
    public MortgageEligibilityCheckResponse checkEligibility(MortgageEligibilityCheckRequest request) {

        log.info("Mortgage eligibility check request:{}", request);

        var violation = rules.validateLoanAmountLimit(request.income(), request.loanAmount(), request.homeValue());
        if (violation != null) return MortgageEligibilityCheckResponse.builder()
                .eligible(false)
                .reason(violation).build();

        if (request.maturityPeriod() > MAX_MATURITY_PERIOD) {
            throw new CustomValidationException(INVALID_MATURITY + request.maturityPeriod());
        }

        var mortgageInterestRate = rateRepo.findByMaturityPeriod(request.maturityPeriod())
                .orElseThrow(() -> new NotFoundException(RATE_NOT_FOUND + request.maturityPeriod()));

        return MortgageEligibilityCheckResponse.builder()
                .eligible(true)
                .reason(ELIGIBLE)
                .interestRate(mortgageInterestRate.getInterestRate()).build();
    }
}
