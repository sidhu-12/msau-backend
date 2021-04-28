package com.accolite.msaumanagementsystemtest.integrationtest.onboardeecucumbertest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.accolite.msaumanagementsystemtest.integrationtest.MSAUIntegrationTest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DeleteOnboardeeStepDefinition  extends MSAUIntegrationTest{
	
	private ResponseEntity<HashMap> response; // output	
	@When("the user calls \\/delete")
	public void the_user_calls_delete() {
		String email = "sanjay@accolitedigital.com";
		response = delete(email);
	}
	
	@Then("the user gets the status code of {int} for deletion of onboardee")
	public void the_user_gets_the_status_code_of(int statusCode) {

	HttpStatus currentStatusCode = response.getStatusCode();
    assertThat("status code is incorrect : "+ 
    response.getBody(), currentStatusCode.value(), is(statusCode));
	}
}


