package com.ing.mortgage.controller;

import com.ing.mortgage.api.dto.MortgageEligibilityCheckRequest;
import com.ing.mortgage.api.dto.MortgageEligibilityCheckResponse;
import com.ing.mortgage.service.MortgageEligibilityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.ing.mortgage.constants.MortgageEligibilityConstants.MORTGAGE_ELIGIBILITY_POST;

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
