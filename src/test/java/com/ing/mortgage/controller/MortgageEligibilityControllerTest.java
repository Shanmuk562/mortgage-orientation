package com.ing.mortgage.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.mortgage.api.dto.MortgageEligibilityCheckRequest;
import com.ing.mortgage.api.dto.MortgageEligibilityCheckResponse;
import com.ing.mortgage.service.MortgageEligibilityService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MortgageEligibilityControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper               objectMapper;
    @MockitoBean
    private MortgageEligibilityService mortgageEligibilityService;
    MortgageEligibilityCheckRequest  request;
    MortgageEligibilityCheckResponse response;

    @BeforeEach
    void setUp() {
        request = MortgageEligibilityCheckRequest.builder()
                .income(BigDecimal.valueOf(10000))
                .maturityPeriod(10)
                .loanAmount(BigDecimal.valueOf(15000))
                .homeValue(BigDecimal.valueOf(50000)).build();

        response = MortgageEligibilityCheckResponse.builder()
                .eligible(true)
                .reason("eligible")
                .interestRate(BigDecimal.valueOf(3.5)).build();
    }

    @SneakyThrows
    @Test
    @DisplayName("""
            200 - SUCCESS
            ↣ GIVEN: Mortgage eligibility check
            ↣ WHEN: valid request
            ↣ THEN: return HttpStatus.OK
            """)
    @Order(1)
    public void mortgageEligibilityCheck_return_httpStatus_200_and_success_response() {
        when(mortgageEligibilityService.checkEligibility(request)).thenReturn(response);
        RequestBuilder requestBuilder = buildEligibilityPostRequest(request);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("""
            400 - BAD REQUEST
            ↣ GIVEN: Mortgage eligibility check
            ↣ WHEN: Invalid request
            ↣ THEN: return HttpStatus.BAD_REQUEST
            """)
    @Order(2)
    public void badRequest_if_loanAmount_is_null() {
        request = MortgageEligibilityCheckRequest.builder()
                .income(BigDecimal.valueOf(10000))
                .maturityPeriod(10)
                .loanAmount(null)
                .homeValue(BigDecimal.valueOf(50000)).build();
        RequestBuilder requestBuilder = buildEligibilityPostRequest(request);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("""
            400 - BAD REQUEST
            ↣ GIVEN: Mortgage eligibility check
            ↣ WHEN: Invalid request
            ↣ THEN: return HttpStatus.BAD_REQUEST
            """)
    @Order(3)
    public void badRequest_if_income_is_null() {
        request = MortgageEligibilityCheckRequest.builder()
                .income(null)
                .maturityPeriod(10)
                .loanAmount(BigDecimal.valueOf(100000))
                .homeValue(BigDecimal.valueOf(50000)).build();
        RequestBuilder requestBuilder = buildEligibilityPostRequest(request);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("""
            400 - BAD REQUEST
            ↣ GIVEN: Mortgage eligibility check
            ↣ WHEN: Invalid request
            ↣ THEN: return HttpStatus.BAD_REQUEST
            """)
    @Order(4)
    public void badRequest_if_home_value_is_null() {
        request = MortgageEligibilityCheckRequest.builder()
                .income(BigDecimal.valueOf(10000))
                .maturityPeriod(10)
                .loanAmount(BigDecimal.valueOf(100000))
                .homeValue(null).build();
        RequestBuilder requestBuilder = buildEligibilityPostRequest(request);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("""
            400 - BAD REQUEST
            ↣ GIVEN: Mortgage eligibility check
            ↣ WHEN: Invalid maturity
            ↣ THEN: return HttpStatus.BAD_REQUEST
            """)
    @Order(5)
    public void badRequest_If_invalid_maturity_period() {
        request = MortgageEligibilityCheckRequest.builder()
                .income(BigDecimal.valueOf(10000))
                .maturityPeriod(null)
                .loanAmount(BigDecimal.valueOf(10000))
                .homeValue(BigDecimal.valueOf(50000)).build();
        RequestBuilder requestBuilder = buildEligibilityPostRequest(request);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @SneakyThrows
    @Test
    @DisplayName("""
            500 - INTERNAL SERVER ERROR
            ↣ GIVEN: Mortgage eligibility check
            ↣ WHEN: valid request but internal server error
            ↣ THEN: return HttpStatus.INTERNAL_SERVER_ERROR
            """)
    @Order(6)
    public void mortgageCheckTestIfServerError() {
        doThrow(new RuntimeException("Something went wrong")).when(mortgageEligibilityService).checkEligibility(request);
        RequestBuilder builder = buildEligibilityPostRequest(request);
        mockMvc.perform(builder)
                        .andExpect(status().is5xxServerError()) // Assert HTTP status code
                .andExpect(jsonPath("$.message").value("Something went wrong")); // Assert error message
    }

    private MockHttpServletRequestBuilder buildEligibilityPostRequest(MortgageEligibilityCheckRequest request) throws JsonProcessingException {
        return MockMvcRequestBuilders
                .post("/api/v1/mortgage/mortgage-eligibility-check")
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .accept(String.valueOf(MediaType.APPLICATION_JSON))
                .content(objectMapper.writeValueAsBytes(request));
    }
}