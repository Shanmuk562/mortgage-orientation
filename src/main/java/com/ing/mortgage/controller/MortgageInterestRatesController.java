package com.ing.mortgage.controller;

import com.ing.mortgage.api.dto.MortgageInterestRatesResponse;
import com.ing.mortgage.service.MortgageInterestRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ing.mortgage.constants.MortgageInterestRateConstants.MORTGAGE_RATES_GET;

@RestController
@RequiredArgsConstructor
public class MortgageInterestRatesController {

    private final MortgageInterestRateService mortgageInterestRateService;

    @GetMapping(MORTGAGE_RATES_GET)
    public ResponseEntity<MortgageInterestRatesResponse> getInterestRates() {
        final MortgageInterestRatesResponse response = mortgageInterestRateService.getInterestRates();
        return ResponseEntity.ok(response);
    }
}
