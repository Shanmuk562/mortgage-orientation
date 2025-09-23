package com.ing.mortgage.mapper;

import com.ing.mortgage.api.dto.MortgageInterestRate;
import com.ing.mortgage.entity.model.InterestRateEntity;
import org.mapstruct.Mapper;

/**
 * Mapper for interestRateMapper.
 */
@Mapper(componentModel = "spring")
public interface MortgageInterestRateMapper {
    MortgageInterestRate toMortgageInterestRate(InterestRateEntity interestRateEntity);
}
