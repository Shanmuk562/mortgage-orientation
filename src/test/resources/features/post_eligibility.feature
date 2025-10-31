@createME
Feature: Eligibility check
  Mortgage eligibility
  url -> /api/v1/mortgage/mortgage-eligibility-check. Mortgage eligibility request has the following JSON format
    {
        "income": 100000,
        "maturityPeriod": 10,
        "loanAmount": 40000,
        "homeValue": 500000
    }

  Scenario: SUCCESSFUL SCENARIO ➤ [ Return Mortgage eligibility]
    Given the MortgageEligibilityCheckRequest in requestBody
    When the Mortgage eligibility endpoint is called
    Then return HttpStatus.OK

   Scenario: FAILURE SCENARIO ➤ [ Mortgage eligibility request with out maturityPeriod ]
             Given the MortgageEligibilityCheckRequest in requestBody with out maturityPeriod
             When the Mortgage eligibility endpoint is called
             Then return HttpStatus.BAD_REQUEST