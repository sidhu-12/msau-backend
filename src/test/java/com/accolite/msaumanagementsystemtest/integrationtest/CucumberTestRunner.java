package com.accolite.msaumanagementsystemtest.integrationtest;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features" , glue = {"LoginStepDefinition"})
public class CucumberTestRunner extends MSAUIntegrationTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
