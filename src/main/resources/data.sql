create table IF NOT EXISTS mortgage_interest_rates
(
    ID             INTEGER auto_increment
        primary key,
    interest_rate   DOUBLE PRECISION,
    maturity_period INTEGER,
    last_updated    TIMESTAMP
);
insert into mortgage_interest_rates (interest_rate, maturity_period, last_updated) values (6.4,10,TIMESTAMP WITH TIME ZONE '2025-06-07 12:30:000');
insert into mortgage_interest_rates (interest_rate, maturity_period, last_updated) values (4.3,25,TIMESTAMP WITH TIME ZONE '2025-02-11 12:30:000');
insert into mortgage_interest_rates (interest_rate, maturity_period, last_updated) values (3.5,30,TIMESTAMP WITH TIME ZONE '2025-09-23 12:30:000');

