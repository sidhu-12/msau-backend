package com.accolite.msaumanagementsystemtest.integrationtest.onboardeecucumbertest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.accolite.msaumanagementsystemtest.integrationtest.MSAUIntegrationTest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ListAllStepDefinition extends MSAUIntegrationTest {

	private ResponseEntity<List> response; // output	
	@When("the user calls \\/listAllAttribute")
	public void the_user_calls_listAllAttribute() {
		String attribute = "location";
		response = listAll(attribute);
	}
	
	@Then("the user gets the status code of {int} for list value for given attribute")
	public void the_user_gets_the_status_code_of(int statusCode) {

	HttpStatus currentStatusCode = response.getStatusCode();
    assertThat("status code is incorrect : "+ 
    response.getBody(), currentStatusCode.value(), is(statusCode));
	}
	
}
