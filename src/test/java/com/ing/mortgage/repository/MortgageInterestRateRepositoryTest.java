package com.ing.mortgage.repository;

import com.ing.mortgage.entity.model.InterestRateEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class MortgageInterestRateRepositoryTest {

    @Autowired
    MortgageInterestRateRepository mortgageInterestRateRepository;

    @BeforeEach
    void setUp() {
        InterestRateEntity interestRateEntity = new InterestRateEntity();
        interestRateEntity.setInterestRate(BigDecimal.ONE);
        interestRateEntity.setMaturityPeriod(4);
        interestRateEntity.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));
        mortgageInterestRateRepository.save(interestRateEntity);
    }

    @Test
    void findByMaturityPeriod() {
        Optional<InterestRateEntity> interestRateEntity =
                mortgageInterestRateRepository.findByMaturityPeriod(4);
        assertThat(interestRateEntity.isPresent()).isTrue();
    }
    @Test
    void findAll() {
        List<InterestRateEntity> interestRateEntities =
                mortgageInterestRateRepository.findAll();
        assertThat(interestRateEntities.size()).isEqualTo(4);
    }
}