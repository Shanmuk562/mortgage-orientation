package com.ing.mortgage.repository;


import com.ing.mortgage.entity.model.InterestRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for mortgage interest rates.
 */
@Repository
public interface MortgageInterestRateRepository extends JpaRepository<InterestRateEntity, Long> {
    Optional<InterestRateEntity> findByMaturityPeriod(Integer maturityPeriod);
}