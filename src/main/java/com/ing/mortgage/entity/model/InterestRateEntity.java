package com.ing.mortgage.entity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Entity for table mortgage_interest_rates table.
 */
@Entity
@Data
@Table(name ="mortgage_interest_rates")
public class InterestRateEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "interest_rate")
    @Min(value = 0, message = "Minimum value is zero for Interest Rate.")
    @NotNull(message = "Interest Rate can not be null.")
    private BigDecimal interestRate;
    @Min(value = 0, message = "Minimum value is zero for Maturity Period.")
    @Column(name = "maturity_period")
    private Integer maturityPeriod;
    @Column(name = "last_updated")
    private Timestamp lastUpdated;
}
