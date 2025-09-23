package com.ing.mortgage.controller;

import com.ing.mortgage.api.dto.MortgageInterestRate;
import com.ing.mortgage.api.dto.MortgageInterestRatesResponse;
import com.ing.mortgage.service.MortgageInterestRateService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MortgageInterestRatesControllerTest {

    @Autowired
    public MockMvc                     mockMvc;
    @MockitoBean
    public MortgageInterestRateService mortgageInterestRateService;
    MortgageInterestRate rate;

    @BeforeEach
    void setUp() {

        rate = new MortgageInterestRate(1L, BigDecimal.valueOf(10.1), 10,
                        Timestamp.from(Instant.now()));
    }

    @SneakyThrows
    @Test
    @DisplayName("""
            200 - SUCCESS
            ↣ GIVEN: Mortgage interest rates
            ↣ WHEN: valid request
            ↣ THEN: return HttpStatus.OK
            """)
    @Order(1)
    public void getInterestRates_success() {
        List<MortgageInterestRate> interestRates = List.of(rate);
        when(mortgageInterestRateService.getInterestRates()).thenReturn(MortgageInterestRatesResponse.builder().interestRates(interestRates).build());
       MvcResult result  = mockMvc.perform(get("/api/v1/mortgage/interest-rates"))
                .andExpect(status().isOk())
               .andExpect(content().contentType("application/json"))
                .andReturn();
        Assertions.assertNotNull(result.getResponse().getContentAsString());
    }

    @SneakyThrows
    @Test
    @DisplayName("""
            500 - INTERNAL SERVER ERROR
            ↣ GIVEN: Mortgage interest rates
            ↣ WHEN: Invalid Uri
            ↣ THEN: return HttpStatus.INTERNAL_SERVER_ERROR
            """)
    @Order(2)
    public void getInterestRates_when_server_failed_incorrect_path() {
        mockMvc.perform(get("/api/v1/mortgage/interest-rate"))
                .andExpect(status().is5xxServerError());
    }
}