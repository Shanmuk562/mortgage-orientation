package com.ing.mortgage.service;

import com.ing.mortgage.api.dto.MortgageInterestRatesResponse;
import com.ing.mortgage.mapper.MortgageInterestRateMapper;
import com.ing.mortgage.repository.MortgageInterestRateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

@SpringBootTest
class MortgageInterestRateServiceImplTest {
    private MortgageInterestRateServiceImpl mortgageInterestRateService;
    @Autowired
    private MortgageInterestRateRepository  mortgageInterestRateRepository;
    @Autowired
    private CacheManager                    cacheManager;
    @Autowired
    private MortgageInterestRateMapper      interestRateMapper;


    @BeforeEach
    void setUp() {
        mortgageInterestRateService = new MortgageInterestRateServiceImpl(mortgageInterestRateRepository, cacheManager, interestRateMapper);
    }

    @Test
    void testStoredInterestRates() {
        MortgageInterestRatesResponse response = mortgageInterestRateService.getInterestRates();
        Assertions.assertFalse(response.interestRates().isEmpty());
    }

    @Test
    void isCached() {
       Cache cache = mortgageInterestRateService.isCached();
       Assertions.assertNotNull(cache);
    }
}