package com.bank.mortgage.cucumber;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bank.mortgage.api.dto.MortgageEligibilityCheckRequest;
import com.bank.mortgage.repository.MortgageInterestRateRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;

import static com.bank.mortgage.constants.MortgageEligibilityConstants.MORTGAGE_ELIGIBILITY_POST;
import static com.bank.mortgage.constants.MortgageInterestRateConstants.MORTGAGE_RATES_GET;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Data
@RequiredArgsConstructor
public class BaseSteps {

    private final MockMvc mockMvc;

    private        String        requestBody;
    private        ResultActions resultActions;
    private        JSONObject  responseContentJson;

    @Autowired
    private MortgageInterestRateRepository mortgageInterestRateRepository;

    @Before
    public void setup() {
        mortgageInterestRateRepository.deleteAll();
    }

    @SneakyThrows
    @Given("the MortgageEligibilityCheckRequest in requestBody")
    public void givenMortgageEligibilityCheckRequestInRequestBody() {
        MortgageEligibilityCheckRequest mortgageEligibilityCheckRequest = createMortgageEligibilityCheckRequest();
        requestBody = new ObjectMapper().writeValueAsString(mortgageEligibilityCheckRequest);
    }

    @SneakyThrows
    @Given("the MortgageEligibilityCheckRequest in requestBody with out maturityPeriod")
    public void givenMortgageEligibilityCheckRequestInRequestBodyWithOutMaturityPeriod() {
        MortgageEligibilityCheckRequest mortgageEligibilityCheckRequest = MortgageEligibilityCheckRequest.builder()
                .homeValue(BigDecimal.valueOf(500000))
                .loanAmount(BigDecimal.valueOf(40000))
                .income(BigDecimal.valueOf(100000)).build();
        requestBody = new ObjectMapper().writeValueAsString(mortgageEligibilityCheckRequest);
    }

    private MortgageEligibilityCheckRequest createMortgageEligibilityCheckRequest() {
       return MortgageEligibilityCheckRequest.builder()
               .homeValue(BigDecimal.valueOf(500000))
               .loanAmount(BigDecimal.valueOf(40000))
               .income(BigDecimal.valueOf(100000))
               .maturityPeriod(10).build();
    }


    @SneakyThrows
    @When("the Mortgage eligibility endpoint is called")
    public void callMortgageEligibilityEndpoint() {
        resultActions = mockMvc.perform(post(MORTGAGE_ELIGIBILITY_POST)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(requestBody))
                .andDo(print());
    }

    @SneakyThrows
    @When("the ReadMortgageInterestRates endpoint is called")
    public void callReadMortgageInterestRatesEndpoint() {
        resultActions = mockMvc.perform(get(MORTGAGE_RATES_GET)
                        .contentType(APPLICATION_JSON_VALUE))
                .andDo(print());
    }

    @SneakyThrows
    @Then("return MortgageInterestRatesResponse in the mentioned format")
    public void checkFormatOfReturnedMortgageInterestRatesResponse(String expectedFormat) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseContentJson = objectMapper.readTree(resultActions.andReturn().getResponse().getContentAsString());
        JsonNode expectedJson = objectMapper.readTree(expectedFormat);
        assertEquals(expectedJson, responseContentJson);
    }


    @SneakyThrows
    @Then("return HttpStatus.OK")
    public void isHttpStatusOK() {
        resultActions.andExpect(status().isOk());
    }

}

