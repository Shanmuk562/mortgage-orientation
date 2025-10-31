package com.bank.mortgage.repository;


import com.bank.mortgage.entity.model.InterestRateEntity;
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