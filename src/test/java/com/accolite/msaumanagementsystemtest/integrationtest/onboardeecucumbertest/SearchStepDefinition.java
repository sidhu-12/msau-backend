package com.accolite.msaumanagementsystemtest.integrationtest.onboardeecucumbertest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.accolite.msaumanagementsystemtest.integrationtest.MSAUIntegrationTest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchStepDefinition extends MSAUIntegrationTest {

	private ResponseEntity<List> response; // output	
	@When("the user calls \\/searchResult")
	public void the_user_calls_search_result() {
		Map<String, String> attributeValue = new HashMap<String, String>();
		response = search(attributeValue);
	}

	@Then("the user gets the status code of {int} for search value")
	public void the_user_gets_the_status_code_of_for_search_value(Integer statusCode) {
		HttpStatus currentStatusCode = response.getStatusCode();
	    assertThat("status code is incorrect : "+ 
	    response.getBody(), currentStatusCode.value(), is(statusCode));
}
}
