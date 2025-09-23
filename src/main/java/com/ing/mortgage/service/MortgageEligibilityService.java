package com.ing.mortgage.service;

import com.ing.mortgage.api.dto.MortgageEligibilityCheckRequest;
import com.ing.mortgage.api.dto.MortgageEligibilityCheckResponse;

public interface MortgageEligibilityService {
    MortgageEligibilityCheckResponse checkEligibility(MortgageEligibilityCheckRequest request);
}
