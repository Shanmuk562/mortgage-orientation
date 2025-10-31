@readIR
Feature: Read Mortgage interest rates
  To read Mortgage interest rates
  url -> /api/v1/mortgage/interest-rates

  The response from the server must have the following format:
  {
    "interestRates": [
      {
        "id": 1,
        "interestRate": 6.4,
        "maturityPeriod": 10,
        "lastUpdated": "2025-06-07T10:30:00.000+00:00"
      },
      {
        "id": 2,
        "interestRate": 4.3,
        "maturityPeriod": 25,
        "lastUpdated": "2025-02-11T11:30:00.000+00:00"
      },
      {
        "id": 3,
        "interestRate": 3.5,
        "maturityPeriod": 30,
        "lastUpdated": "2025-09-23T10:30:00.000+00:00"
      }
    ]
  }
  First we populate the DB with mortgage interest rates in order to ensure that a record exists and we can fetch it.

  Scenario: SUCCESS SCENARIO ➤ [ Get mortgage interest rates ]
    When the ReadMortgageInterestRates endpoint is called
    Then return HttpStatus.OK
    And  return MortgageInterestRatesResponse in the mentioned format
    """
   {
     "interestRates": [
       {
         "id": 1,
         "interestRate": 6.4,
         "maturityPeriod": 10,
         "lastUpdated": "2025-06-07T10:30:00.000+00:00"
       },
       {
         "id": 2,
         "interestRate": 4.3,
         "maturityPeriod": 25,
         "lastUpdated": "2025-02-11T11:30:00.000+00:00"
       },
       {
         "id": 3,
         "interestRate": 3.5,
         "maturityPeriod": 30,
         "lastUpdated": "2025-09-23T10:30:00.000+00:00"
       }
     ]
   }
   """