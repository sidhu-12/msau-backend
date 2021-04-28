package com.accolite.msaumanagementsystemtest.integrationtest.onboardeecucumbertest;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.accolite.msaumanagement.model.Onboardee;
import com.accolite.msaumanagementsystemtest.integrationtest.MSAUIntegrationTest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UpdateOnboardeeStepDefinition extends MSAUIntegrationTest{

	private ResponseEntity<HashMap> response; // output	
	@When("the user calls \\/update")
	public void the_user_calls_update() {
	
		Map<String, List<String>> updateMap = new HashMap<String, List<String>>();
		List<String> attributeList = new ArrayList<String>();
		List<String> valuesList = new ArrayList<String>();
		List<String> email = new ArrayList<String>();
		attributeList.add("phoneNo");
		attributeList.add("location");
		updateMap.put("attributes",attributeList);
		valuesList.add("9994644711");
		valuesList.add("Jaipur");
		updateMap.put("values",valuesList);
		email.add("sidharth.kumar1@gmail.com");
		updateMap.put("email", email);
		response = update(updateMap);
}

	@Then("the user gets the status code of {int} for updation")
	public void the_user_gets_the_status_code_of(int statusCode) {

	HttpStatus currentStatusCode = response.getStatusCode();
    assertThat("status code is incorrect : "+ 
    response.getBody(), currentStatusCode.value(), is(statusCode));
	}
}
