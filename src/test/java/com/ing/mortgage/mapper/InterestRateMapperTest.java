package com.ing.mortgage.mapper;

import com.ing.mortgage.api.dto.MortgageInterestRate;
import com.ing.mortgage.entity.model.InterestRateEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mapstruct.factory.Mappers.getMapper;


class InterestRateMapperTest {
    private InterestRateEntity interestRateEntity;

    @MockitoBean
    private MortgageInterestRateMapper mortgageInterestRateMapper = getMapper(MortgageInterestRateMapper.class);

    @BeforeEach
    void setup() {
        interestRateEntity = new InterestRateEntity();
        interestRateEntity.setId(1L);
        interestRateEntity.setInterestRate(BigDecimal.ONE);
        interestRateEntity.setLastUpdated(Timestamp.valueOf("2025-08-08 11:33:33.000"));
        interestRateEntity.setMaturityPeriod(2);
    }

    @Test
    void toMortgageInterestRateWithValidData() {
        MortgageInterestRate mortgageInterestRate = mortgageInterestRateMapper.toMortgageInterestRate(interestRateEntity);
        assertThat(mortgageInterestRate.getInterestRate()).isEqualTo(BigDecimal.ONE);
        assertThat(mortgageInterestRate.getLastUpdated()).isEqualTo(Timestamp.valueOf("2025-08-08 11:33:33.000"));
        assertThat(mortgageInterestRate.getMaturityPeriod()).isEqualTo(2);
    }

    @Test
    void toMortgageRateWithNullEntity() {
        MortgageInterestRate mortgageInterestRate = mortgageInterestRateMapper.toMortgageInterestRate(null);
        assertThat(mortgageInterestRate).isNull();
    }
}