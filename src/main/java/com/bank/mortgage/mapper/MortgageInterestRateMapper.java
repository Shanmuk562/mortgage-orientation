package com.bank.mortgage.mapper;

import com.bank.mortgage.api.dto.MortgageInterestRate;
import com.bank.mortgage.entity.model.InterestRateEntity;
import org.mapstruct.Mapper;

/**
 * Mapper for interestRateMapper.
 */
@Mapper(componentModel = "spring")
public interface MortgageInterestRateMapper {
    MortgageInterestRate toMortgageInterestRate(InterestRateEntity interestRateEntity);
}
