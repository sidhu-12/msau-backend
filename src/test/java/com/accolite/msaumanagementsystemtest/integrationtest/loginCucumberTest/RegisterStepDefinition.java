package com.accolite.msaumanagementsystemtest.integrationtest.loginCucumberTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.accolite.msaumanagement.model.UserLogin;
import com.accolite.msaumanagementsystemtest.integrationtest.MSAUIntegrationTest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterStepDefinition extends MSAUIntegrationTest{

	private ResponseEntity<HashMap> response; // output
	@When("the user clicks the \\/register")
	public void the_user_clicks_the_register() {
	  UserLogin user = new UserLogin("ABC","abc@gmail.com","abcdef",null,false);
		response = register(user);
//	    throw new io.cucumber.java.PendingException();
	}

	@Then("the client receives the status code of {int}")
	public void the_client_receives_the_status_code_of(int statusCode) {
	    // Write code here that turns the phrase above into concrete actions
		HttpStatus currentStatusCode = response.getStatusCode();
	    assertThat("status code is incorrect : "+ 
	    response.getBody(), currentStatusCode.value(), is(statusCode));
//	    throw new io.cucumber.java.PendingException();
	}
}
