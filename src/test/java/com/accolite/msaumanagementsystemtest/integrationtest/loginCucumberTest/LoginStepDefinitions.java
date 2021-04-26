package com.accolite.msaumanagementsystemtest.integrationtest.loginCucumberTest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.accolite.msaumanagementsystemtest.integrationtest.MSAUIntegrationTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginStepDefinitions extends MSAUIntegrationTest {
	
	private ResponseEntity<HashMap> response; // output
	@When("the client calls  login")
	public void the_client_calls_login() throws Exception {
		String username = "sid123@acc.com";
		String password = "abcdef_1234";
		response = authenticate(username, password);
//	    throw new io.cucumber.java.PendingException();
	}
	@Then("^the client receives status code of (\\d+)+$")
	public void the_client_receives_status_code_of(int statusCode) throws Exception{
		HttpStatus currentStatusCode = response.getStatusCode();
	    assertThat("status code is incorrect : "+ 
	    response.getBody(), currentStatusCode.value(), is(statusCode));
	}
	@And("^the client  receives response message as (.+)$")
	public void the_client_receives_response_message_as(String responseMessage) throws Throwable {
	    assertThat(response.getBody().get("responseMessage"), is(responseMessage));
	}
	@When("the client calls  login with invalid email")
	public void the_client_calls_login_with_invalid_email() {
	    // Write code here that turns the phrase above into concrete actions
		String username = "sid1234@acc.com";
		String password = "abcdef_1234";
		response = authenticate(username, password);
	}

	@When("the client calls  login with invalid password")
	public void the_client_calls_login_with_invalid_password() {
		String username = "sid123@acc.com";
		String password = "abcdef_12345";
		response = authenticate(username, password);
	}

}
