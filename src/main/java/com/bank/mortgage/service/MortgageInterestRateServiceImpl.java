package com.bank.mortgage.service;

import com.bank.mortgage.api.dto.MortgageInterestRate;
import com.bank.mortgage.api.dto.MortgageInterestRatesResponse;
import com.bank.mortgage.entity.model.InterestRateEntity;
import com.bank.mortgage.mapper.MortgageInterestRateMapper;
import com.bank.mortgage.repository.MortgageInterestRateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.bank.mortgage.constants.MortgageInterestRateConstants.INTEREST_RATES_CACHE;
import static java.util.Optional.ofNullable;

@Service
@Slf4j
@RequiredArgsConstructor
public class MortgageInterestRateServiceImpl implements MortgageInterestRateService {

    private final MortgageInterestRateRepository mortgageInterestRateRepository;
    private final CacheManager                   cacheManager;
    private final MortgageInterestRateMapper mortgageInterestRateMapper;

    /**
     * This method returns the current mortgage interest rates, maturity periods
     * @return MortgageInterestRatesResponse
     */
    public MortgageInterestRatesResponse getInterestRates() {

        List<InterestRateEntity> interestRateEntityList = mortgageInterestRateRepository.findAll();
        List<MortgageInterestRate> interestRates = interestRateEntityList
                .stream().map(mortgageInterestRateMapper::toMortgageInterestRate).toList();

        Objects.requireNonNull(cacheManager.getCache(INTEREST_RATES_CACHE))
                .put("InterestRateList", interestRates);
        log.info("InterestRateList size: {}" , interestRates.size());
        return MortgageInterestRatesResponse.builder()
                .interestRates(interestRates).build();

    }

    @Cacheable(cacheNames = INTEREST_RATES_CACHE)
    public Cache isCached() {
        getInterestRates();
        return ofNullable(cacheManager.getCache(INTEREST_RATES_CACHE)).orElseThrow();
    }
}
