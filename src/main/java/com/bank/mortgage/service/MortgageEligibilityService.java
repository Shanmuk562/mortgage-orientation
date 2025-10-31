package com.bank.mortgage.service;

import com.bank.mortgage.api.dto.MortgageEligibilityCheckRequest;
import com.bank.mortgage.api.dto.MortgageEligibilityCheckResponse;

public interface MortgageEligibilityService {
    MortgageEligibilityCheckResponse checkEligibility(MortgageEligibilityCheckRequest request);
}
