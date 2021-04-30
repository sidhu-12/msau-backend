package com.accolite.msaumanagementsystemtest.integrationtest.onboardeecucumbertest;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.sql.Date;
import java.util.HashMap;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.accolite.msaumanagement.model.Onboardee;
import com.accolite.msaumanagementsystemtest.integrationtest.MSAUIntegrationTest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateOnboardeeStepDefinition extends MSAUIntegrationTest{

	private ResponseEntity<HashMap> response; // output	
	@When("the user calls \\/create")
	public void the_user_calls_create() {
		long phoneNo = 9876543201L;
		Random random = new Random(3);
		Onboardee newOnboardee = new Onboardee("abc"+random.nextInt()+"@accolitedigital.com","abc",phoneNo,112189,"Chennai","Angular,Java",new Date(2021,6,25),"Pending","Completed");
		response = create(newOnboardee);
}

	@Then("the user gets the status code of {int}")
	public void the_user_gets_the_status_code_of(int statusCode) {

	HttpStatus currentStatusCode = response.getStatusCode();
    assertThat("status code is incorrect : "+ 
    response.getBody(), currentStatusCode.value(), is(statusCode));
	}
}
