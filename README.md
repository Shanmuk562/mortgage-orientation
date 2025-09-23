Mortgage orientation application overview

🔶 This application provides the mortgage eligibility check and current mortgage interest rates 🔶
The application consists of below end points.

• GET /api/v1/mortgage/interest-rates (get the list of current interest rates)

• POST /api/v1/mortgage/mortgage-eligibility-check (mortgage eligibility check based on income, home value, loan amount, maturity period)

Local setup To build and run the application locally, you will need the following:

JDK 21 Maven H2 in memory database from intellij

Build:

    This can be done using the following:
    Maven commands: mvn clean install

Database:

   The application requires below database tables:

    TESTDB Added a configuration in application property.
    run data.sql file before start of the application.
    Refer to src/test/resources/data.sql for the initial database setup.

Spinning Up the server locally:

    First, navigate to the src directory.

    run MortgageOrientationApplication using default application.properties.

    OR use below command

    mvn spring-boot:run

API Documentation http://localhost:8086/v3/api-docs