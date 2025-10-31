package com.bank.mortgage.constants;

public class MortgageEligibilityConstants {

    public static final String MORTGAGE_ELIGIBILITY_POST     = "/api/v1/mortgage/mortgage-eligibility-check";
    public static final String INVALID_INCOME     = "income is invalid";
    public static final String INVALID_MATURITY     = "maturityPeriod is invalid ";
    public static final String INVALID_LOAN_AMOUNT     = "loanAmount is invalid";
    public static final String INVALID_HOME_VALUE     = "homeValue is invalid";
    public static final String RATE_NOT_FOUND = "Rate not found for the maturity period :";
    public static final String ELIGIBLE = "Eligible";
    public static final int MAX_MATURITY_PERIOD = 70; // in years
}
