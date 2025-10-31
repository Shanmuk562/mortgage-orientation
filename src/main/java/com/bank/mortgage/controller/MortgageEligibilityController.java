package com.bank.mortgage.controller;

import com.bank.mortgage.api.dto.MortgageEligibilityCheckRequest;
import com.bank.mortgage.api.dto.MortgageEligibilityCheckResponse;
import com.bank.mortgage.service.MortgageEligibilityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.bank.mortgage.constants.MortgageEligibilityConstants.MORTGAGE_ELIGIBILITY_POST;

@RestController
@RequiredArgsConstructor
public class MortgageEligibilityController {

    private final MortgageEligibilityService mortgageEligibilityService;

    @PostMapping(MORTGAGE_ELIGIBILITY_POST)
    public MortgageEligibilityCheckResponse checkEligibility(@Valid @RequestBody MortgageEligibilityCheckRequest
                                                                     request) {
        return mortgageEligibilityService.checkEligibility(request);
    }
}
